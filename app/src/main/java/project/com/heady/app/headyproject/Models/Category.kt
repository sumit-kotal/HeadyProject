package project.com.heady.app.headyproject.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Category {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("products")
    @Expose
    var products: List<Product>? = null
    @SerializedName("child_categories")
    @Expose
    var childCategories: List<Int>? = null

}
