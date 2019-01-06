package project.com.heady.app.headyproject.Models

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class ProductConverter {
        var gson = Gson()

        @TypeConverter
        fun stringToSomeObjectList(data: String?): List<Product> {
            if (data == null) {
                return Collections.emptyList()
            }

            val listType = object : TypeToken<List<Product>>() {

            }.type

            return gson.fromJson(data, listType)
        }

        @TypeConverter
        fun someObjectListToString(someObjects: List<Product>): String {
            return gson.toJson(someObjects)
        }
}
