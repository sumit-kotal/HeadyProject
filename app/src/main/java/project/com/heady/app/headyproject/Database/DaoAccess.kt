package project.com.heady.app.headyproject.Database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import project.com.heady.app.headyproject.Models.*
import java.sql.RowId

@Dao
interface DaoAccess {

    @Insert//(onConflict = REPLACE)
    fun insertProduct(product: MutableList<FinalProduct>)

    @Insert//(onConflict = REPLACE)
    fun insertVariants(variant: MutableList<FinalVariant>)

    @Query("SELECT * FROM product")
    fun getAllProducts():MutableList<FinalProduct>

    @Query("SELECT * FROM variant")
    fun getAllVariants():MutableList<FinalVariant>

    @Query("DELETE FROM product")
    fun nukeTableProduct()

    @Query("DELETE FROM variant")
    fun nukeTableVariant()

    @Query("SELECT * FROM product ORDER BY view_count ASC")
    fun getAllProductsByViewCountAscending():MutableList<FinalProduct>

    @Query("SELECT * FROM product ORDER BY view_count DESC")
    fun getAllProductsByViewCountDescending():MutableList<FinalProduct>


    @Query("SELECT * FROM product ORDER BY order_count ASC")
    fun getAllProductsByOrderCountAscending():MutableList<FinalProduct>

    @Query("SELECT * FROM product ORDER BY order_count DESC")
    fun getAllProductsByOrderCountDescending():MutableList<FinalProduct>


    @Query("SELECT * FROM product ORDER BY most_shared ASC")
    fun getAllProductsByMostSharedAscending():MutableList<FinalProduct>

    @Query("SELECT * FROM product ORDER BY most_shared DESC")
    fun getAllProductsByMostSharedDescending():MutableList<FinalProduct>

    @Query("SELECT DISTINCT category_name FROM product")
    fun getAllCategories():MutableList<String>


    @Query("SELECT * FROM product where category_name=:cat_name ORDER BY view_count ASC")
    fun getAllProductsByViewCountAscendingOnCategory(cat_name:String):MutableList<FinalProduct>

    @Query("SELECT * FROM product where category_name=:cat_name ORDER BY view_count DESC")
    fun getAllProductsByViewCountDescendingOnCategory(cat_name:String):MutableList<FinalProduct>


    @Query("SELECT * FROM product where category_name=:cat_name ORDER BY order_count ASC")
    fun getAllProductsByOrderCountAscendingOnCategory(cat_name:String):MutableList<FinalProduct>

    @Query("SELECT * FROM product where category_name=:cat_name ORDER BY order_count DESC")
    fun getAllProductsByOrderCountDescendingOnCategory(cat_name:String):MutableList<FinalProduct>


    @Query("SELECT * FROM product where category_name=:cat_name ORDER BY most_shared ASC")
    fun getAllProductsByMostSharedAscendingOnCategory(cat_name:String):MutableList<FinalProduct>

    @Query("SELECT * FROM product where category_name=:cat_name ORDER BY most_shared DESC")
    fun getAllProductsByMostSharedDescendingOnCategory(cat_name:String):MutableList<FinalProduct>

    @Query("SELECT * FROM variant where product_id=:id")
    fun getVariants(id:Int):MutableList<FinalVariant>

}
