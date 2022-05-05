package giorgi.gt.altaapp.DATABASE

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface product_dao {


    @Query("Select * from product_table")
    fun getalldata(): LiveData<List<product_table>>

    @Query("Select * from product_table where title=:title")
    fun get_data_by_title(title:String): product_table


    @Query("Select   Exists (select * from product_table where title=:title)")
    fun check_item(title:String):Boolean


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add_to_favourites(product:product_table)

    @Query("Delete from product_table where product_website_id=:product_website_id")
    fun delete( product_website_id:String)

    @Query("Delete from product_table")
    fun delete_everythin()








}