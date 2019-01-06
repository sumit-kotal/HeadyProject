package project.com.heady.app.headyproject.Models

import android.arch.persistence.room.*
import org.jetbrains.annotations.NotNull

@Entity(tableName = "variant"/*,foreignKeys = arrayOf(
    ForeignKey(
        entity = FinalProduct::class,
        parentColumns = arrayOf("product_id"),
        childColumns = arrayOf("product_id"))
)*/)
data class FinalVariant (

    @ColumnInfo(name = "product_id")
    public var product_id: Int? = 0,

    @ColumnInfo(name = "color")
    public var color: String? = "",

    @ColumnInfo(name = "size")
    public var size: String? = "",

    @ColumnInfo(name = "price")
    public var price: String? = ""

)
{
    @NotNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public var id: Int = 0
}
