package project.com.heady.app.headyproject.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tax {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("value")
    @Expose
    var value: Double? = null

}
