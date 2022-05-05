package giorgi.gt.altaapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import giorgi.gt.altaapp.DATABASE.product_table
import giorgi.gt.altaapp.DATABASE.specmodal
import giorgi.gt.altaapp.adapters.detailed_product_activity.product_detailed_images_adapter
import giorgi.gt.altaapp.adapters.detailed_product_activity.product_specification_adapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class product_detailed_activity : AppCompatActivity() {
    lateinit var favourite_addbtn:Button
    lateinit var favourite_removebtn:Button
    lateinit var toolbar:Toolbar
    lateinit var  product_image_recyclerxml:RecyclerView
    lateinit var product_specification_recyclerXML:RecyclerView
    lateinit var product_id_textview:TextView
    lateinit var product_name_textview:TextView
    lateinit var product_price_textview:TextView
    var isonfav=false

    lateinit var view_modal:viewmodall
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_view_layout)
        var loading_dialog=Dialog(this)
        loading_dialog.setContentView(R.layout.loading_page)
        loading_dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        lifecycleScope.launch {
            loading_dialog.show()
            delay(1200L)
            loading_dialog.dismiss()
        }



        initview()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back_icon)
        view_modal=ViewModelProvider(this)[viewmodall::class.java]


        var lymanager=LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        product_image_recyclerxml.layoutManager=lymanager
        var product_image_adapter= product_detailed_images_adapter(this)

        product_image_recyclerxml.adapter=product_image_adapter
        var lymanager2=LinearLayoutManager(this)
        product_specification_recyclerXML.layoutManager=lymanager2
        var specifiaction_adapter= product_specification_adapter()

        product_specification_recyclerXML.adapter=specifiaction_adapter
        var link=intent.extras?.getString("link")
        var data_type=intent.extras?.getString("type")
        var title=intent.extras?.getString("title")

        fun data_loader(modal:product_table){

            //    checkitem(modal.title)
            product_image_adapter.filldate(modal.all_picture as ArrayList<String>)
            product_image_adapter.notifyDataSetChanged()
            specifiaction_adapter.filldata(modal.specs as ArrayList<specmodal>)
            specifiaction_adapter.notifyDataSetChanged()
            product_name_textview.text=modal.title
            product_price_textview.text=modal.price
            product_id_textview.text=modal.product_website_id


            favourite_addbtn.setOnClickListener {
                favourite_addbtn.visibility= View.GONE
                favourite_removebtn.visibility=View.VISIBLE
                view_modal.add_in_db(product_table(null,modal.price,modal.title,modal.specs,modal.main_picture,modal.all_picture,modal.product_website_id))
                Toast.makeText(this,"წარმატებით დაემატა ფავორიტებში", Toast.LENGTH_SHORT).show()

            }

            favourite_removebtn.setOnClickListener {
                favourite_addbtn.visibility=View.VISIBLE
                favourite_removebtn.visibility=View.GONE

                view_modal.delete_product(modal.product_website_id   )
                Toast.makeText(this,"წარმატებით წაიშალა", Toast.LENGTH_SHORT).show()

            }

        }

        if(data_type=="INTERNET"){

            if (title != null) {
                view_modal.checkitem_in_db(title).observe(this, Observer {
                        it->
                    if(it==true){

                        favourite_addbtn.visibility=View.GONE
                        favourite_removebtn.visibility=View.VISIBLE
                        view_modal.get_product_by_title(title).observe(this, Observer {
                                modal->
                            data_loader(modal)

                        })

                    }else{
                        favourite_addbtn.visibility=View.VISIBLE
                        favourite_removebtn.visibility= View.GONE
                        view_modal.get_product_detailed_view(link.toString()).observe(this, Observer {
                           var mod=product_table(null,it.price,it.title,it.specs,it.main_picture,it.all_picture,it.product_website_id)
                            data_loader(mod)

                        })




                    }

                }


                )
            }



        }
        if(data_type=="DATABASE"){
            println("1111111111111111111111111111111")
            favourite_addbtn.visibility=View.GONE
            favourite_removebtn.visibility=View.VISIBLE
            if (title != null) {
                view_modal.get_product_by_title(title).observe(this, Observer {

                    data_loader(it)
                })
            }






        }







    }



            fun initview(){



              favourite_addbtn =findViewById(R.id.favourite_btn_add_btn)
               favourite_removebtn =findViewById(R.id.favourite_btn_remove_btn)




                toolbar=findViewById<Toolbar>(R.id.toolbarr)
                product_image_recyclerxml=findViewById<RecyclerView>(R.id.product_photos_recycler)
                product_specification_recyclerXML =findViewById(R.id.product_specification_recycler)
                product_id_textview=findViewById(R.id.detailed_productid_textview)
                product_name_textview=findViewById(R.id.detailed_product_nametextview)
                product_price_textview=findViewById(R.id.detailed_product_price_textview)










            }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }



}