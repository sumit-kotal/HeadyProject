package project.com.heady.app.headyproject.network

import project.com.heady.app.headyproject.Models.Data
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @get:GET("json")
    val data: Call<Data>

    companion object {
        const val BASE_URL = "https://stark-spire-93433.herokuapp.com/"
    }
}
