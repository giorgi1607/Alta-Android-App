package giorgi.gt.altaapp

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import giorgi.gt.altaapp.DATABASE.specmodal
import kotlinx.serialization.Serializable
import java.lang.reflect.Type

@Serializable
class converters()  {


//    @TypeConverter
//    fun arraystringToJsonString(value: Array<String>?)= value?.let { Json.encodeToString(value)}
//
//    @TypeConverter
//    fun StringToList(value: String) = value?.let {Json.decodeFromString< Array<String>>(value)}
//

//
//
//    @TypeConverter
//    fun listToJsonString(value: Array<specmodal>?)=  value?.let { Json.encodeToString(value) }
//
//    @TypeConverter
//    fun jsonStringToList(value: String) = value.let {Json.decodeFromString< Array<specmodal>>(value)}




    @JvmName("fromOptionValuesList1")
    @TypeConverter // note this annotation
    fun fromOptionValuesList(optionValues: List<specmodal?>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<specmodal?>?>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter
    fun toOptionValuesList(optionValuesString: String?): List<specmodal>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<specmodal?>?>() {}.type
        return gson.fromJson<List<specmodal>>(optionValuesString, type)
    }





    @TypeConverter // note this annotation
    fun fromValuesList(optionValues: List<String?>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<String?>?>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter
    fun  oOptionValuesList(optionValuesString: String?): List<String>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson<List<String>>(optionValuesString, type)
    }






//
//    @TypeConverter // note this annotation
//    fun fromOptionValuesList(optionValues: List<OptionValues?>?): String? {
//        if (optionValues == null) {
//            return null
//        }
//        val gson = Gson()
//        val type: Type = object : TypeToken<List<OptionValues?>?>() {}.type
//        return gson.toJson(optionValues, type)
//    }
//
//    @TypeConverter // note this annotation
//    fun toOptionValuesList(optionValuesString: String?): List<OptionValues>? {
//        if (optionValuesString == null) {
//            return null
//        }
//        val gson = Gson()
//        val type: Type = object : TypeToken<List<OptionValues?>?>() {}.type
//        return gson.fromJson<List<OptionValues>>(optionValuesString, type)
//    }








//        Gson().fromJson(value, Array<product_table>::class.java).toList()


//    @TypeConverter
//    fun fromList(value : List<String>) = Json.encodeToString(value)


}