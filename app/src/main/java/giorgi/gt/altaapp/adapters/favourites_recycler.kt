package giorgi.gt.altaapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import giorgi.gt.altaapp.DATABASE.product_standart_modal
import giorgi.gt.altaapp.DATABASE.product_table
import giorgi.gt.altaapp.R
import giorgi.gt.altaapp.clickinterfaces.clicker

class favourites_recycler (var context: Context,var clicker:clicker):RecyclerView.Adapter<favourites_recycler.myholder>() {

    var data=ArrayList<product_table>()
    fun filldata(getdata:ArrayList<product_table>){
        this.data=getdata
        notifyDataSetChanged()


    }









    class myholder(itemView: View,var clickerr:clicker) :RecyclerView.ViewHolder(itemView){
        val image:ImageView=itemView.findViewById(R.id.mainfragmetrecycler_image)
        val title:TextView=itemView.findViewById(R.id.mainfragmetrecycler_title)
        val price:TextView=itemView.findViewById(R.id.mainfragmetrecycler_price)









    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myholder {
       val view :View=LayoutInflater.from(parent.context).inflate(R.layout.item_design_linear,null)
        var hold=myholder(view,clicker)
        return hold

    }

    override fun onBindViewHolder(holder: myholder, position: Int) {
                var modal=data[position]
                var st= product_standart_modal(modal.price,modal.title,modal.main_picture,null)

        Glide.with(context).load(modal.all_picture?.get(0)?.toString()).into( holder.image)
        holder.title.text=modal.title
        holder.price.text=modal.price.toString()

        holder.itemView.setOnClickListener {
            holder.clickerr.click(st)
        }






    }







    override fun getItemCount(): Int {
        return data.size
    }


}