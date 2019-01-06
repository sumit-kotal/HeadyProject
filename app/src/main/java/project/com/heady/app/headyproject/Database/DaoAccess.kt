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
/*
    @Query("SELECT * FROM variant WHERE product_id == :id")
    fun getVariantDataFromId(id:Int):MutableList<Variant>

    @Query("SELECT product.* , variant.* FROM product JOIN variant ON id = product_id ORDER BY most_shared DESC")
    fun getAllJoined():MutableList<FinalProduct>*/


}
