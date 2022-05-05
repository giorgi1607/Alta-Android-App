package giorgi.gt.altaapp.adapters.main_fragment_adapters

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

class main_fragment_item_adapter(val context: Context, var click:clicker) :PagingDataAdapter<product_standart_modal, main_fragment_item_adapter.myholder>(utill)   {





companion object{
    private val utill=object:DiffUtil.ItemCallback<product_standart_modal>(){
        override fun areItemsTheSame(oldItem: product_standart_modal, newItem: product_standart_modal): Boolean {
           return oldItem ==oldItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: product_standart_modal, newItem: product_standart_modal): Boolean {
           return oldItem ==oldItem


        }
    }



}




class myholder(itemView: View,var clicklisten:clicker) :RecyclerView.ViewHolder(itemView){


val image:ImageView=itemView.findViewById(R.id.mainfragmetrecycler_image)
val title:TextView=itemView.findViewById(R.id.mainfragmetrecycler_title)
val price:TextView=itemView.findViewById(R.id.mainfragmetrecycler_price)








}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myholder {
      val view:View=LayoutInflater.from(parent.context).inflate(R.layout.item_design_linear,null)
       val hold=myholder(view,click)
       return hold



    }

    override fun onBindViewHolder(holder: myholder, position: Int) {
        val modal=getItem(position)


        Glide.with(context).load(modal?.main_picture).into( holder.image)
        holder.title.text=modal?.title
        holder.price.text=modal?.price.toString()+"₾"
        holder.itemView.setOnClickListener {
            if (modal != null) {
                holder.clicklisten.click(modal)
            }



        }

    }



}