package giorgi.gt.altaapp

import giorgi.gt.altaapp.DATABASE.product_dao
import giorgi.gt.altaapp.DATABASE.product_table

class repo(val dao:product_dao) {

     val get_all_data=dao.getalldata()


        suspend fun get_product_by_title(title:String)=
            dao.get_data_by_title(title)




        fun get_data_byid(title: String){
            dao.get_data_by_title(title)

        }


    suspend fun insert_in_db(product:product_table){

        dao.add_to_favourites(product)
    }

    suspend fun delete_product_from_db(  product_website_id:String){
        dao.delete(product_website_id)
    }
    suspend fun delete_everything_from_db(){
        dao.delete_everythin()
    }
    suspend fun checkitem_in_db(tite:String)=dao.check_item(tite)















}