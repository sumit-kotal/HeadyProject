package project.com.heady.app.headyproject.Models

import android.arch.persistence.room.*
import org.jetbrains.annotations.NotNull

@Entity(tableName = "product"/*,foreignKeys = arrayOf(ForeignKey(
    entity = FinalVariant::class,
    parentColumns = arrayOf("product_id"),
    childColumns = arrayOf("product_id"))
)*/)
data class FinalProduct (

    @ColumnInfo(name = "product_id")
    public var product_id: Int = 0,

    @ColumnInfo(name = "name")
    public var name: String? = "",

    @ColumnInfo(name = "date_added")
    public var date_added: String? = "",

    @ColumnInfo(name = "tax_name")
    public var tax_name: String? = "",

    @ColumnInfo(name = "tax_value")
    public var tax_value: Double = 0.0,

    @ColumnInfo(name = "category_name")
    public var category_name: String = "",

    @ColumnInfo(name = "view_count")
    public var view_count: Int = 0,

    @ColumnInfo(name = "order_count")
    public var order_count: Int = 0,

    @ColumnInfo(name = "most_shared")
    public var most_shared: Int = 0

   /* @ColumnInfo(name = "child_category")
    @TypeConverters(ChildCategoryConverter::class)
    public var child_category: List<Int>? = null*/

)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public var id: Int = 0
}
