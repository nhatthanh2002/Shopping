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
import com.nhatthanh.shopping.product.fragment.HomeFragment
import com.nhatthanh.shopping.product.listenerevent.GetPositionItem
import com.nhatthanh.shopping.product.model.MainData
import com.nhatthanh.shopping.product.model.Notifications
import com.nhatthanh.shopping.product.model.Products
import com.nhatthanh.shopping.product.model.SeeAll

class TestAdapter(
    private val context: Context,
    private val listObject: List<MainData>,
    private val getPositionItem: GetPositionItem
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class HorizontalViewHolder(val binding: ItemParentNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val rvNotifications=binding.rvNotification
    }

    @Suppress("DEPRECATION")
    inner class GridViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Products) {
            with(binding) {
                with(item) {
                    tvNameProduct.text = name
                    tvPromotion.text = price.toString()
                    tvPriceProduct.text = sale_price.toString()
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
        return listObject[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            Utils.TYPE_NOTIFICATION -> return HorizontalViewHolder(
                ItemParentNotificationBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
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
            Utils.TYPE_NOTIFICATION -> {
                val horizontalViewHolder: HorizontalViewHolder = holder as HorizontalViewHolder
                val adapterN=NotificationAdapter(context,listObject[position].listNotifications)
                horizontalViewHolder.rvNotifications.apply {
                    setHasFixedSize(true)
                    adapter=adapterN
                }
            }
            Utils.TYPE_SEE_ALL -> {
                val verticalViewHolder: VerticalViewHolder = holder as VerticalViewHolder
                verticalViewHolder.bind(listObject[position].listSeeAll[position])
            }
            else -> {
                val gridViewHolder: GridViewHolder = holder as GridViewHolder
                gridViewHolder.bind(listObject[position].listProducts[position])
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