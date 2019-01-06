package project.com.heady.app.headyproject.Views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import project.com.heady.app.headyproject.Database.RoomDbCall
import project.com.heady.app.headyproject.DbOperations
import project.com.heady.app.headyproject.Models.Category
import project.com.heady.app.headyproject.Models.Ranking
import project.com.heady.app.headyproject.ProductAdapter
import project.com.heady.app.headyproject.ProductViewModel
import project.com.heady.app.headyproject.R


class MainActivity : AppCompatActivity() {

    lateinit var product_recycler: RecyclerView

    val db: DbOperations = DbOperations(this@MainActivity)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        product_recycler = findViewById(R.id.product_recycler)
        product_recycler.layoutManager = LinearLayoutManager(this@MainActivity)

        val model = ViewModelProviders.of(this).get<ProductViewModel>(ProductViewModel::class.java)

        model.getProduct().observe(this, Observer { p ->
            val category = p?.categories
            val ranking = p?.rankings

            category?.let { ranking?.let { it1 -> db.saveDb(it as MutableList<Category>, it1 as MutableList<Ranking>) } }

        })

        callAsynTask()

    }

    fun callAsynTask(){
        AsyncTask.execute {
            val dbCall = RoomDbCall.getProducts(this@MainActivity)
            val products = dbCall.daoAccess().getAllProductsByViewCountAscending()

            Log.d("product","${products.size}  is this")

            if(products.isNotEmpty()) {
                product_recycler.adapter =
                        ProductAdapter(products, context = applicationContext)
            }
        }
    }


}