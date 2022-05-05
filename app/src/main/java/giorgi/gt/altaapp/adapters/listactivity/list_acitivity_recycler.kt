package giorgi.gt.altaapp.adapters.listactivity

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import giorgi.gt.altaapp.DATABASE.product_standart_modal
import giorgi.gt.altaapp.R
import giorgi.gt.altaapp.clickinterfaces.clicker

class list_acitivity_recycler(var context:Context,var clicklisten:clicker) :PagingDataAdapter<product_standart_modal, list_acitivity_recycler.myholder>(utill) {




companion object{
        var layouttype =R.layout.item_design_linear

    private val utill=object: DiffUtil.ItemCallback<product_standart_modal>(){
        override fun areItemsTheSame(oldItem: product_standart_modal, newItem: product_standart_modal): Boolean {
            return oldItem ==oldItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: product_standart_modal, newItem: product_standart_modal): Boolean {
            return oldItem ==oldItem


        }
    }



}






    class myholder(itemView: View,  ly: Int,var clicklisten:clicker) :RecyclerView.ViewHolder(itemView){

        lateinit var image:ImageView
        lateinit var title:TextView
        lateinit var price:TextView

        init {
            if(ly==R.layout.item_design_linear){
          image=itemView.findViewById(R.id.mainfragmetrecycler_image)
             title =itemView.findViewById(R.id.mainfragmetrecycler_title)
              price   =itemView.findViewById(R.id.mainfragmetrecycler_price)


        }
            if(ly==R.layout.item_design_grid){

             image=itemView.findViewById(R.id.gridlayout_imageview)
                title =itemView.findViewById(R.id.gridlayout_title)
                price   =itemView.findViewById(R.id.gridlayout_price)


            }

        }



         }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myholder {

        val view:View=LayoutInflater.from(parent.context).inflate(layouttype,null)


        val hold=myholder(view,layouttype,clicklisten)

        return hold


    }

    override fun onBindViewHolder(holder: myholder, position: Int) {
        var modal=getItem(position)

        Glide.with(context).load(modal?.main_picture).into( holder.image)
        holder.title.text=modal?.title
        holder.price.text=modal?.price.toString()
        holder.itemView.setOnClickListener {

            if (modal != null) {
                clicklisten.click(modal)
            }

        }



    }




}