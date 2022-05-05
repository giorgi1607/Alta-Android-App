package giorgi.gt.altaapp.DATABASE

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import giorgi.gt.altaapp.converters

@Entity
class product_table (
    @PrimaryKey(autoGenerate = true)

    val id: Long?,
    val price:String,
    val title:String,
    @TypeConverters(converters::class)
    val specs:List<specmodal>,
    val main_picture:String,
    @TypeConverters(converters::class)
    val all_picture:List<String>,
    val product_website_id:String
        )
