package giorgi.gt.altaapp.fragments

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import giorgi.gt.altaapp.DATABASE.product_standart_modal
import giorgi.gt.altaapp.R
import giorgi.gt.altaapp.adapters.main_fragment_adapters.main_fragment_item_adapter
import giorgi.gt.altaapp.clickinterfaces.clicker
import giorgi.gt.altaapp.product_detailed_activity
import giorgi.gt.altaapp.viewmodall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class search_fragment : Fragment() , clicker {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view=LayoutInflater.from(context).inflate(R.layout.search_fragment,null)

        var view_modal= ViewModelProvider(this)[viewmodall::class.java]
        var search_text=view.findViewById<EditText>(R.id.search_text_view)
        var searchbtn: Button =view.findViewById(R.id.searchbtn_m)

        var recyclerxml=view.findViewById<RecyclerView>(R.id.search_recyclerxml)
        var adapter= context?.let { main_fragment_item_adapter(it,this) }
        var lymanager=LinearLayoutManager(context)
        recyclerxml.layoutManager=lymanager
        recyclerxml.adapter=adapter

        searchbtn.setOnClickListener {
            var inputed_text=search_text.text.toString()


            if(!inputed_text.isEmpty() ){
            var loading_dialog= context?.let { it1 -> Dialog(it1) }
            loading_dialog?.setContentView(R.layout.loading_page)
            loading_dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            lifecycleScope.launch {
                loading_dialog?.show()
                delay(1200L)
                loading_dialog?.dismiss()
            }

                lifecycleScope.launch(Dispatchers.IO)  {

                view_modal.search_product_paging(inputed_text).collect{

                    adapter?.submitData(it)


                }

            }
        }else{
            Toast.makeText(context,"საძიებო ველი ცარიელია ",Toast.LENGTH_SHORT).show()

        }


        }





























        return  view
    }

    override fun click(item: product_standart_modal) {
        var intent:Intent= Intent(context,product_detailed_activity::class.java)
        intent.putExtra("link",item.detailed_link)
        intent.putExtra("type","INTERNET")
        intent.putExtra("title",item.title)
        startActivity(intent)
    }


}