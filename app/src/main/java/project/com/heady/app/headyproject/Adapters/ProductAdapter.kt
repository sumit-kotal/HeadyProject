package project.com.heady.app.headyproject.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.product_item.view.*
import project.com.heady.app.headyproject.Database.RoomDbCall
import project.com.heady.app.headyproject.Models.FinalProduct
import project.com.heady.app.headyproject.Models.FinalVariant
import project.com.heady.app.headyproject.R
import java.text.SimpleDateFormat
import java.util.*

class ProductAdapter(val items : List<FinalProduct>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Inflates the item views
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        Log.d("inside adap","inside")
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.product_item,
                p0,
                false
            )
        )
    }

    // Binds each product in the ArrayList to a view
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productName.text = items[position].name
        holder.dateAdded.text = "Added on : ${dateTime(items[position].date_added)}"
        holder.viewCount.text = items[position].view_count.toString()
        holder.orderCount.text = items[position].order_count.toString()
        holder.mostShared.text = items[position].most_shared.toString()
        holder.taxName.text = items[position].tax_name
        holder.taxValue.text = items[position].tax_value.toString()
        holder.categoryName.text = "Category : ${items[position].category_name}"

        holder.variantRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        AsyncTask.execute {

            holder.variantRecycler.recycledViewPool.clear()

            val dbCall = RoomDbCall.getProducts(context)

            val variants: MutableList<FinalVariant>? = dbCall.daoAccess().getVariants(items[position].product_id)

            Log.d("Variant Size", variants?.size.toString())

            holder.variantRecycler.adapter =
                    VariantAdapter(
                        variants!!,
                        context = context
                    )

            holder.variantRecycler.post {
                if(holder.variantRecycler.adapter!=null)
                    (holder.variantRecycler.adapter as VariantAdapter).notifyDataSetChanged()
            }


        }




    }

    // Gets the number of products in the list
    override fun getItemCount(): Int {
        return items.size
    }


}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each product to
    val productName = view.product_name
    val dateAdded = view.date_added
    val viewCount = view.view_count
    val orderCount = view.order_count
    val mostShared = view.most_shared
    val taxName = view.tax_name
    val taxValue = view.tax_value
    val categoryName = view.category_name
    val variantRecycler = view.variant_recycler

}

fun dateTime(dateString: String?): String {
    Log.d("235",dateString)

    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    input.timeZone = TimeZone.getTimeZone("UTC")

    val dt = input.parse(dateString)
    Log.d("235",dt.toString())



    val sdfDtTime = SimpleDateFormat("d MMM yyyy hh:mm a")

    val sdfDate = SimpleDateFormat("dd MMM yyyy")
    sdfDate.timeZone = java.util.TimeZone.getTimeZone("GMT")
    Log.d("235",sdfDate.format(dt))
    Log.d("235",sdfDtTime.format(dt))


    val sdfTime = SimpleDateFormat("hh:mm a")
    sdfTime.timeZone = java.util.TimeZone.getTimeZone("GMT")


    return sdfDtTime.format(dt)
}
