package com.updown.superdo.ui.main

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.updown.superdo.R
import com.updown.superdo.data.ProductInfo
import kotlinx.android.synthetic.main.item_product.view.*


class ProductsFeedAdapter(var data: List<ProductInfo>) :
    RecyclerView.Adapter<ProductsFeedAdapter.FeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder =
        FeedViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_product,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.itemView.txtview_product_name.text = data[position].name
        holder.itemView.txtview_product_weight.text = data[position].weight
        holder.itemView.imgview_product.apply {
            val gd = GradientDrawable()
            gd.setColor(Color.parseColor(data[position].bagColor)) // (no gradient)
            gd.setStroke(2, Color.BLACK)
            gd.shape = GradientDrawable.OVAL
            gd.gradientType = GradientDrawable.RADIAL_GRADIENT
            gd.gradientRadius = width / 2f
            background = gd
        }
    }

    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun updateList(newList: List<ProductInfo>) {
        data = newList
        notifyDataSetChanged()
//        val diffResult = DiffUtil.calculateDiff(MyDiffCallback(this.data, newList))
//        diffResult.dispatchUpdatesTo(this)
    }
}

//class MyDiffCallback(private val oldData: List<ProductInfo>, private val newData: List<ProductInfo>) :
//    DiffUtil.Callback() {
//    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return oldData[oldItemPosition] == newData[newItemPosition]
//    }
//
//    override fun getOldListSize(): Int = oldData.size
//
//    override fun getNewListSize(): Int = newData.size
//
//    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return oldData[oldItemPosition].name == newData[newItemPosition].name &&
//                oldData[oldItemPosition].weight == newData[newItemPosition].weight &&
//                oldData[oldItemPosition].bagColor == newData[newItemPosition].bagColor
//    }
//}