package giorgi.gt.altaapp.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import giorgi.gt.altaapp.DATABASE.product_standart_modal
import giorgi.gt.altaapp.R
import giorgi.gt.altaapp.adapters.main_fragment_adapters.banner_pageadapter
import giorgi.gt.altaapp.adapters.main_fragment_adapters.main_fragment_item_adapter
import giorgi.gt.altaapp.clickinterfaces.clicker
import giorgi.gt.altaapp.list_activity
import giorgi.gt.altaapp.product_detailed_activity
import giorgi.gt.altaapp.viewmodall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class main_fragment : Fragment(),clicker {

        lateinit var banner_recyclerxml:RecyclerView
        lateinit var items_recyclerxml:RecyclerView
        lateinit var category_notebookbtn:ImageButton
        lateinit var category_phonebtn:ImageButton
        lateinit var category_splitbtn:ImageButton
        lateinit var category_kitchentechbtn:ImageButton
    lateinit var view_modal:viewmodall
        @RequiresApi(Build.VERSION_CODES.N)
        var c=1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view_modal=ViewModelProvider(this)[viewmodall::class.java]

            val view=inflater.inflate(R.layout.fragment_main_fragment, container, false)
        banner_recyclerxml=view.findViewById (R.id.banner_recyclerxml)
        items_recyclerxml=view.findViewById(R.id.mainfragmet_itemrecycler)

        category_notebookbtn=view.findViewById(R.id.category_notebookbtn)
        category_phonebtn=view.findViewById(R.id.category_phonebtnn)
        category_splitbtn=view.findViewById(R.id.category_split)
        category_kitchentechbtn=view.findViewById(R.id.category_kitchen)






       val banner_adapter= context?.let { banner_pageadapter(it) }
            val lymanager=LinearLayoutManager(context)
            val lymanager2=LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)



         val item_adapter=context?.let { main_fragment_item_adapter(it,this)}
            items_recyclerxml.layoutManager=lymanager
            banner_recyclerxml.layoutManager=lymanager2
            banner_recyclerxml.adapter=banner_adapter
           items_recyclerxml.adapter=item_adapter



        lifecycleScope.launch(Dispatchers.IO) {
        view_modal.get_split_paging().collect{

            item_adapter?.submitData(it)


        }
    }






        var rand= Random
            lifecycleScope.launch(Dispatchers.IO) {

                view_modal.getbanner_pager().collect{
                    banner_adapter?.submitData(it)
            }



            }




        lifecycleScope.launch (Dispatchers.IO){

            while(true){
                val int=rand.nextInt(0,10)


                withContext(Dispatchers.Main  ){

                    banner_recyclerxml.smoothScrollToPosition(int)
                    delay(2000L)
                }





            }


        }







        category_notebookbtn.setOnClickListener {


            val intent : Intent =Intent(context,list_activity::class.java)
            intent.putExtra("type",1)
            startActivity(intent)


            }

            category_phonebtn.setOnClickListener {
                val intent : Intent =Intent(context,list_activity::class.java)
                intent.putExtra("type",2)
                startActivity(intent)

            }
            category_splitbtn.setOnClickListener {
                val intent : Intent =Intent(context,list_activity::class.java)
                intent.putExtra("type",3)
                startActivity(intent)

            }

            category_kitchentechbtn.setOnClickListener {
                val intent : Intent =Intent(context,list_activity::class.java)
                intent.putExtra("type",4)
                startActivity(intent)


            }















        return  view














    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun click(item: product_standart_modal) {



        var intent:Intent= Intent(context,product_detailed_activity::class.java)
        intent.putExtra("link",item.detailed_link)
        intent.putExtra("type","INTERNET")
        intent.putExtra("title",item.title)
        startActivity(intent)

    }


}