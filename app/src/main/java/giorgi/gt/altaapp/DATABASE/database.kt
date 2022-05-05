package giorgi.gt.altaapp.DATABASE

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import giorgi.gt.altaapp.converters

@Database(entities = [product_table::class ],exportSchema = false,version = 1)
@TypeConverters(converters::class )
abstract class database : RoomDatabase()  {
    abstract fun productdao():product_dao
    companion object{

        @Volatile
        var INSTANCE :database?=null



        fun createdb(context:Context):database{
            var instance= INSTANCE
            if (instance!=null){return instance}
            synchronized(this){
                var instancev= Room.databaseBuilder(context,database::class.java,"product_database").build()
                INSTANCE=instancev
                return instancev

            }




        }




    }











}