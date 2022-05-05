package giorgi.gt.altaapp.adapters.detailed_product_activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import giorgi.gt.altaapp.DATABASE.specmodal
import giorgi.gt.altaapp.R

class product_specification_adapter : RecyclerView.Adapter<product_specification_adapter.holder>() {
var  data=ArrayList<specmodal>()


 fun filldata(givendata:ArrayList<specmodal> ){

     this.data=givendata






 }






class holder(itemView: View):RecyclerView.ViewHolder(itemView){
    var product_title=itemView.findViewById<TextView>(R.id.specification_key)
    var product_value=itemView.findViewById<TextView>(R.id.specification_value)




}

    override fun onBindViewHolder(holder: holder, position: Int) {


        holder.product_title.text= data[position].speckey
        holder.product_value.text=  data[position].specvalue



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.product_specification_item_design,null)
        var hold= holder(view)

        return hold


    }

    override fun getItemCount(): Int {
        return data.size
    }


}