package project.com.heady.app.headyproject

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import android.util.Log
import project.com.heady.app.headyproject.Database.RoomDbCall
import project.com.heady.app.headyproject.Models.Data
import project.com.heady.app.headyproject.Models.FinalProduct
import project.com.heady.app.headyproject.Models.FinalVariant
import project.com.heady.app.headyproject.network.Api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductViewModel : ViewModel() {


    //this is the data that we will fetch asynchronously
    var data: MutableLiveData<Data>? = null

    //we will call this method to get the data
    fun getProduct(): LiveData<Data> {
        //if the list is null
        if (data == null) {
            data = MutableLiveData()
            //we will load it asynchronously from server in this method
            loadProduct()
        }

        //finally we will return the list
        return data as MutableLiveData<Data>
    }


    //This method is using Retrofit to get the JSON data from URL
    private fun loadProduct() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create<Api>(Api::class.java)
        val call = api.data


        call.enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                data!!.value = response.body()
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.d("I am on failure","fail  "+t.message)
            }
        })
    }

}
