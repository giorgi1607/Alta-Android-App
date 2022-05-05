package giorgi.gt.altaapp.fragments

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import giorgi.gt.altaapp.DATABASE.product_standart_modal
import giorgi.gt.altaapp.DATABASE.product_table
import giorgi.gt.altaapp.R
import giorgi.gt.altaapp.adapters.favourites_recycler
import giorgi.gt.altaapp.clickinterfaces.clicker
import giorgi.gt.altaapp.product_detailed_activity
import giorgi.gt.altaapp.viewmodall


class favourite_fragment : Fragment() ,clicker{
        lateinit var favourite_recyclerxml:RecyclerView
            lateinit var all_fav_remove_btn:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View=inflater.inflate(R.layout.fragment_favourite_fragment, container, false)
         favourite_recyclerxml=view.findViewById(R.id.favourite_recyclerxml)
        all_fav_remove_btn=view.findViewById(R.id.all_favourite_remove_btn)

        var view_modal= ViewModelProvider(this)[viewmodall::class.java]
        var dg= context?.let { it1 -> Dialog(it1) }
        dg?.setContentView(R.layout.dialog_remove_confirm)
        var removebtn=dg?.findViewById<Button>(R.id.dialog_removebt)
        var nobtn= dg?.findViewById<Button>(R.id.dialog_nobtn)

        dg?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        removebtn?.setOnClickListener {
            view_modal.delete_everything_fromlocaldb()
            dg?.dismiss()
        }

        nobtn?.setOnClickListener {
            dg?.dismiss()
        }



          val recycler_adapter= context?.let { favourites_recycler(it,this) }
            val lymanager=LinearLayoutManager(context)
        favourite_recyclerxml.layoutManager=lymanager
        favourite_recyclerxml.adapter=recycler_adapter



        all_fav_remove_btn.setOnClickListener {
            dg?.show()
        }

        view_modal.get_database_data.observe(viewLifecycleOwner, Observer {
            it->
            recycler_adapter?.filldata(it as ArrayList<product_table>)


        })







        return view
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun click(item: product_standart_modal) {


        var intent: Intent = Intent(context, product_detailed_activity::class.java)
       intent.putExtra("title",item.title)
        intent.putExtra("type","DATABASE")

//
     startActivity(intent)


    }


}