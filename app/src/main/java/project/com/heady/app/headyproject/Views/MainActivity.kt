package project.com.heady.app.headyproject.Views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import project.com.heady.app.headyproject.Database.RoomDbCall
import project.com.heady.app.headyproject.Database.SaveToDb
import project.com.heady.app.headyproject.Models.Category
import project.com.heady.app.headyproject.Models.Ranking
import project.com.heady.app.headyproject.Adapters.ProductAdapter
import project.com.heady.app.headyproject.ProductViewModel
import project.com.heady.app.headyproject.R
import android.widget.ArrayAdapter
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import project.com.heady.app.headyproject.Models.FinalProduct


class MainActivity : AppCompatActivity() {

    var cat_select_position = 0
    var cat_select_string = ""
    var sort_position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        product_recycler.layoutManager = LinearLayoutManager(this@MainActivity)

        val sort_adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.spinner))
        sort_spinner.adapter = sort_adapter

        loadCategories()

        val model = ViewModelProviders.of(this).get<ProductViewModel>(ProductViewModel::class.java)

        model.getProduct().observe(this, Observer { p ->
            val category = p?.categories
            val ranking = p?.rankings

            category?.let { ranking?.let { it1 -> SaveToDb(this@MainActivity)
                .saveDb(it as MutableList<Category>, it1 as MutableList<Ranking>) } }
        })

        sort_spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View, position: Int, id: Long
            ) {
                sort_position = position
                callAsynTask()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        category_spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View, position: Int, id: Long
            ) {
                cat_select_position = position
                cat_select_string =  category_spinner.getItemAtPosition(position).toString()
                callAsynTask()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

    }


    fun loadCategories(){
        AsyncTask.execute {
            val dbCall = RoomDbCall.getProducts(this@MainActivity)

            val categories = dbCall.daoAccess().getAllCategories()

            categories.add(0,"All")

            runOnUiThread {
                val category_adapter =
                    ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
                category_spinner.adapter = category_adapter
            }

        }
    }

    fun callAsynTask(){
        AsyncTask.execute {
            val dbCall = RoomDbCall.getProducts(this@MainActivity)

            var products: MutableList<FinalProduct>? = ArrayList<FinalProduct>()

            if (cat_select_position==0) {
                when (sort_position) {
                    0 -> products = dbCall.daoAccess().getAllProductsByViewCountAscending()
                    1 -> products = dbCall.daoAccess().getAllProductsByViewCountDescending()
                    2 -> products = dbCall.daoAccess().getAllProductsByOrderCountAscending()
                    3 -> products = dbCall.daoAccess().getAllProductsByOrderCountDescending()
                    4 -> products = dbCall.daoAccess().getAllProductsByMostSharedAscending()
                    5 -> products = dbCall.daoAccess().getAllProductsByMostSharedDescending()
                }
            }
            else{
                when (sort_position) {
                    0 -> products = dbCall.daoAccess().getAllProductsByViewCountAscendingOnCategory(cat_select_string)
                    1 -> products = dbCall.daoAccess().getAllProductsByViewCountDescendingOnCategory(cat_select_string)
                    2 -> products = dbCall.daoAccess().getAllProductsByOrderCountAscendingOnCategory(cat_select_string)
                    3 -> products = dbCall.daoAccess().getAllProductsByOrderCountDescendingOnCategory(cat_select_string)
                    4 -> products = dbCall.daoAccess().getAllProductsByMostSharedAscendingOnCategory(cat_select_string)
                    5 -> products = dbCall.daoAccess().getAllProductsByMostSharedDescendingOnCategory(cat_select_string)
                }
            }

            runOnUiThread {
                // Stuff that updates the UI
                if(products?.isNotEmpty()!!) {
                    product_recycler.adapter =
                            ProductAdapter(
                                products,
                                context = applicationContext
                            )
                    (product_recycler.adapter as ProductAdapter).notifyDataSetChanged()
                }
            }


        }
    }


}