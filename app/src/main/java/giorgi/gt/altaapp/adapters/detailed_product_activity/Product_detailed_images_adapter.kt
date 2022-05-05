package giorgi.gt.altaapp.adapters.detailed_product_activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import giorgi.gt.altaapp.R

class product_detailed_images_adapter (var context: Context) : RecyclerView.Adapter <product_detailed_images_adapter.myholder> (){
    var data=ArrayList<String>()


    @SuppressLint("NotifyDataSetChanged")
    fun filldate(data:ArrayList<String>){
        this.data=data
        notifyDataSetChanged()

    }




    class myholder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var image=itemView.findViewById<ImageView>(R.id.banner_rectcler_image)





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myholder {
        var view= LayoutInflater.from(parent.context).inflate(R.layout.banner_item_recycler,null)

        var hold= myholder(view)





        return hold




    }


    override fun onBindViewHolder(holder: myholder, position: Int) {



        Glide.with(context).load(data[position]).into( holder.image)
        var dg= Dialog(context)
        dg.setContentView(R.layout.picture_zoom_dialog)
        var btn=dg.findViewById<Button>(R.id.dialog_exitbtb)
        var img=dg.findViewById<ImageView>(R.id.dialog_imageviwe)

        Glide.with(context).load(data[position]).into(img)
        dg.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        btn.setOnClickListener { dg.dismiss() }






        holder.image.setOnClickListener{



            dg.show()

        }









    }

    override fun getItemCount(): Int {



        return data.size
    }


}