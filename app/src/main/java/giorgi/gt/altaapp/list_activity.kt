package giorgi.gt.altaapp
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.chip.ChipGroup
import giorgi.gt.altaapp.DATABASE.product_standart_modal
import giorgi.gt.altaapp.adapters.listactivity.list_acitivity_recycler
import giorgi.gt.altaapp.clickinterfaces.clicker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class list_activity : AppCompatActivity(),clicker {
    lateinit var list_recyclerxml:RecyclerView
    lateinit var gridmodebtn:ImageButton
    lateinit var linearmodebtm:ImageButton
    lateinit var title:TextView
    lateinit var chipgrop:ChipGroup
lateinit var tb: Toolbar
lateinit var adapter:list_acitivity_recycler
lateinit var view_modal:viewmodall





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initview()
        setSupportActionBar(tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back_icon)
        supportActionBar?.title=null

        adapter=list_acitivity_recycler( this,this)
        view_modal= ViewModelProvider(this)[viewmodall::class.java]
        var type=intent.extras?.getInt("type")
        var lymanager =LinearLayoutManager(this )



        list_recyclerxml.layoutManager=lymanager
        list_recyclerxml.adapter=adapter




        if(type==1){
            title.text="LAPTOPS"
            lifecycleScope.launch (Dispatchers.IO){
                view_modal.notebook_category_handler().collect {

                    adapter.submitData(it)

                }
            }

        }
        if(type==2){
            title.text="SMARTPHONES"
            lifecycleScope.launch (Dispatchers.IO){
                view_modal.smartphone_split_paging().collect {

                    adapter.submitData(it)

                }
            }

        }
        if(type==3){
            title.text="SPLIT SYSTEMS"
            lifecycleScope.launch (Dispatchers.IO){
                view_modal.get_split_paging().collect {

                    adapter.submitData(it)

                }
            }

        }
        if(type==4){
            title.text="KITCHEN"
            lifecycleScope.launch (Dispatchers.IO){
                view_modal.get_kitchen_pageing().collect {

                    adapter.submitData(it)

                }
            }

        }





        gridmodebtn.setOnClickListener {


            gridmodebtn.visibility= View.GONE
            linearmodebtm .visibility= View.VISIBLE
            gridmanager()

        }
        linearmodebtm.setOnClickListener {
            gridmodebtn.visibility= View.VISIBLE
            linearmodebtm .visibility= View.GONE

            linearmamanger()
        }



















    }
    fun initview(){
        tb=findViewById(R.id.toolbarrr)
        list_recyclerxml=findViewById(R.id.list_activity_recyclerxml)
        gridmodebtn=findViewById(R.id.listactivity_gridmode)
        linearmodebtm=findViewById(R.id.listactivity_linearmode)
        title=findViewById(R.id.listactivity_title)













    }


    fun gridmanager(){
        var lymanager= StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)



        list_acitivity_recycler.layouttype=R.layout.item_design_grid

        list_recyclerxml.layoutManager=lymanager
        list_recyclerxml.adapter=adapter



    }

    fun linearmamanger(){

        var lymanager=LinearLayoutManager(this )

        list_acitivity_recycler.layouttype=R.layout.item_design_linear
        list_recyclerxml.layoutManager=lymanager
        list_recyclerxml.adapter=adapter





    }

    override fun click(item: product_standart_modal) {
        var intent: Intent = Intent(this,product_detailed_activity::class.java)
        intent.putExtra("link",item.detailed_link)
        intent.putExtra("type","INTERNET")
        intent.putExtra("title",item.title)
        startActivity(intent)

    }


}