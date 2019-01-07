package project.com.heady.app.headyproject.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.variant_item.view.*
import project.com.heady.app.headyproject.Models.FinalVariant
import project.com.heady.app.headyproject.R

class VariantAdapter(val items : List<FinalVariant>, val context: Context) : RecyclerView.Adapter<VariantViewHolder>() {

    // Inflates the item views
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VariantViewHolder {
        Log.d("inside adap","inside")
        return VariantViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.variant_item,
                p0,
                false
            )
        )
    }

    // Binds each variantt in the ArrayList to a view
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VariantViewHolder, position: Int) {
        holder.variant_color.text = items[position].color
        holder.variant_size.text = items[position].size
        holder.variant_price.text = items[position].price
    }

    // Gets the number of variantts in the list
    override fun getItemCount(): Int {
        return items.size
    }


}

class VariantViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each variantt to
    val variant_color = view.variant_color
    val variant_size = view.variant_size
    val variant_price = view.variant_price

}
