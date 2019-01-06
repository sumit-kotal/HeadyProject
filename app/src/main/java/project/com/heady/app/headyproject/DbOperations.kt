package project.com.heady.app.headyproject

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.AsyncTask
import android.util.Log
import project.com.heady.app.headyproject.Database.RoomDbCall
import project.com.heady.app.headyproject.Models.Category
import project.com.heady.app.headyproject.Models.FinalProduct
import project.com.heady.app.headyproject.Models.FinalVariant
import project.com.heady.app.headyproject.Models.Ranking

class DbOperations internal constructor(internal var activity: Activity) {

    fun saveDb(categoryData: MutableList<Category>,rankingData: MutableList<Ranking>) {

                //finally we are setting the list to our MutableLiveData
                AsyncTask.execute {
                    // Insert Data

                    var dbCall = RoomDbCall.getProducts(activity)
                    var dbCall2 = RoomDbCall.getVariants(activity)

                    if (dbCall.daoAccess().getAllProducts().isNotEmpty())
                        dbCall.daoAccess().nukeTableProduct()

                    if (dbCall2.daoAccess().getAllVariants().isNotEmpty())
                        dbCall2.daoAccess().nukeTableVariant()

                    var finalProduct: MutableList<FinalProduct>? = ArrayList<FinalProduct>()

                    for(i in 0 until categoryData!!.size)
                    {
                        var finalResult: FinalProduct? = null
                        val category_name = categoryData[i].name
                        val product_cat = categoryData[i].products
                        for(j in 0 until product_cat!!.size)
                        {
                            val product_name = product_cat[j].name
                            val product_date = product_cat[j].dateAdded
                            val product_tax_name = product_cat[j].tax?.name
                            val product_tax_value = product_cat[j].tax?.value
                            val product_id = product_cat[j].id

                            var view_count = 0
                            var order_count = 0
                            var most_shared = 0

                            for(k in 0 until rankingData!!.size)
                            {
                                val prod_rank = rankingData[k].products
                                for(l in 0 until prod_rank!!.size)
                                {
                                    val id = prod_rank[l].id
                                    if (id==product_id)
                                    {
                                        if (prod_rank[l].viewCount != null) {
                                            view_count = prod_rank[l].viewCount!!
                                        }
                                        if (prod_rank[l].orderCount != null) {
                                            order_count = prod_rank[l].orderCount!!
                                        }
                                        if (prod_rank[l].shares != null) {
                                            most_shared = prod_rank[l].shares!!
                                        }
                                    }
                                }
                            }

                            finalResult = FinalProduct(product_id!!,product_name,product_date,product_tax_name,
                                product_tax_value!!,
                                category_name!!,
                                view_count,order_count,most_shared)

                            finalProduct?.add(finalResult)


                            //Variant Table Add
                            var finalVariant: MutableList<FinalVariant>? = ArrayList<FinalVariant>()

                            val variant = product_cat[j].variants
                            var finalVariantItem: FinalVariant?
                            for (x in 0 until variant!!.size)
                            {
                                finalVariantItem = FinalVariant(product_id,variant[x].color,
                                    variant[x].size?.toString(), variant[x].price?.toString()
                                )
                                finalVariant?.add(finalVariantItem)
                            }
                            finalVariant?.let { dbCall2.daoAccess().insertVariants(it) }
                        }
                    }
                    finalProduct?.let { dbCall.daoAccess().insertProduct(it) }
                }
        Log.d("Finished ","Saving to Db")
    }
/*
    fun getAllProductsByViewCountAscending():LiveData<MutableList<FinalProduct>>
    {
        var p: MutableList<FinalProduct> ?= null
        AsyncTask.execute {
            val dbCall = RoomDbCall.getProducts(activity)
            p = dbCall.daoAccess().getAllProductsByViewCountAscending()
        }


    }*/
/*

    fun getAllProductsByViewCountDescending():LiveData<MutableList<FinalProduct>>
    {
        var dbCall = RoomDbCall.getProducts(activity)
        return dbCall.daoAccess().getAllProductsByViewCountDescending()
    }

    fun getAllProductsByOrderCountAscending():LiveData<MutableList<FinalProduct>>
    {
        var dbCall = RoomDbCall.getProducts(activity)
        return dbCall.daoAccess().getAllProductsByOrderCountAscending()
    }
*/

    fun getAllProductsByOrderCountDescending():MutableList<FinalProduct>
    {
        var dbCall = RoomDbCall.getProducts(activity)
        return dbCall.daoAccess().getAllProductsByOrderCountDescending()
    }

    fun getAllProductsByMostSharedAscending():MutableList<FinalProduct>
    {
        var dbCall = RoomDbCall.getProducts(activity)
        return dbCall.daoAccess().getAllProductsByMostSharedAscending()
    }

    fun getAllProductsByMostSharedDescending():MutableList<FinalProduct>
    {
        var dbCall = RoomDbCall.getProducts(activity)
        return dbCall.daoAccess().getAllProductsByMostSharedDescending()
    }


}
