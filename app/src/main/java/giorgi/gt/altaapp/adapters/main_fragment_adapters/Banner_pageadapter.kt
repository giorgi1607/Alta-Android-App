package giorgi.gt.altaapp.adapters.main_fragment_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import giorgi.gt.altaapp.R

class banner_pageadapter(var context:Context) :PagingDataAdapter <String, banner_pageadapter.myholder> (utill){
    var data=ArrayList<Int>()



companion object{

    private val utill=object: DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
           return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem==newItem
        }
    }
    }








    class myholder(itemView: View) :RecyclerView.ViewHolder(itemView){

        var image=itemView.findViewById<ImageView>(R.id.banner_rectcler_image)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myholder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.banner_item_recycler,null)

        var hold= myholder(view)
        return hold
    }

    override fun onBindViewHolder(holder: myholder, position: Int) {
            val modal=getItem(position)

        Glide.with(context).load( modal).into( holder.image)









    }



}