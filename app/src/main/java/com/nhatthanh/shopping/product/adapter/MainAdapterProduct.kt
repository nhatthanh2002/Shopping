package com.nhatthanh.shopping.product.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nhatthanh.shopping.Utils
import com.nhatthanh.shopping.databinding.ItemNotificationBinding
import com.nhatthanh.shopping.databinding.ItemParentNotificationBinding
import com.nhatthanh.shopping.databinding.ItemProductBinding
import com.nhatthanh.shopping.databinding.ItemSeeAllBinding
import com.nhatthanh.shopping.product.listenerevent.GetPositionItem
import com.nhatthanh.shopping.product.model.Notifications
import com.nhatthanh.shopping.product.model.Products
import com.nhatthanh.shopping.product.model.SeeAll

class MainAdapterProduct(
    private val context: Context,
    private val listObject: List<Any>,
    private val getPositionItem: GetPositionItem
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    inner class HorizontalViewHolder(val binding: ItemNotificationBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(item: Notifications) {
//            with(binding) {
//                with(item) {
//                    Glide.with(context)
//                        .load(image)
//                        .override(250, 120)
//                        .centerCrop()
//                        .into(banner)
//                }
//            }
//        }
//    }

    @Suppress("DEPRECATION")
    inner class GridViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Products) {
            with(binding) {
                with(item) {
                    tvNameProduct.text = name
                    tvPromotion.text = Utils.formatCurrency.format(price).toString()
                    tvPriceProduct.text = Utils.formatCurrency.format(sale_price).toString()
                    tvRate.text = star.toString()
                    tvBrandStoreProduct.text = seller
                    Glide.with(context)
                        .load(image)
                        .override(180, 160)
                        .centerCrop()
                        .into(imgProduct)
                    layoutProduct.setOnClickListener {
                        getPositionItem.getItem(id)
                    }
                }
            }
        }
    }

    inner class VerticalViewHolder(val binding: ItemSeeAllBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SeeAll) {
            with(binding) {
                with(item) {
                    tvProduct.text = newProduct
                    tvSeeAll.text = seeAll
                }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (listObject[position]) {
//            is Notifications -> Utils.TYPE_NOTIFICATION
            is SeeAll -> Utils.TYPE_SEE_ALL
            else -> Utils.TYPE_PRODUCT

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
//            Utils.TYPE_NOTIFICATION -> return HorizontalViewHolder(
//                ItemNotificationBinding.inflate(
//                    LayoutInflater.from(parent.context), parent, false
//                )
//            )
            Utils.TYPE_SEE_ALL -> return VerticalViewHolder(
                ItemSeeAllBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            else -> return GridViewHolder(
                ItemProductBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
//            Utils.TYPE_NOTIFICATION -> {
//                val horizontalViewHolder: HorizontalViewHolder = holder as HorizontalViewHolder
//                horizontalViewHolder.bind(listObject[position] as Notifications)
//            }
            Utils.TYPE_SEE_ALL -> {
                val verticalViewHolder: VerticalViewHolder = holder as VerticalViewHolder
                verticalViewHolder.bind(listObject[position] as SeeAll)
            }
            else -> {
                val gridViewHolder: GridViewHolder = holder as GridViewHolder
                gridViewHolder.bind(listObject[position] as Products)
            }
        }
    }

    //get query, path
    //post
    // dung post man để request thử

    override fun getItemCount(): Int {
        Log.e("SizeList", "${listObject.size}")
        return listObject.size

    }
}