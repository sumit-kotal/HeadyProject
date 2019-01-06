package project.com.heady.app.headyproject.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {

    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null

    @SerializedName("rankings")
    @Expose
    var rankings: List<Ranking>? = null

}
