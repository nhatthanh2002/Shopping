package com.nhatthanh.shopping.product.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhatthanh.shopping.Utils
import com.nhatthanh.shopping.databinding.ItemNotificationBinding
import com.nhatthanh.shopping.databinding.ItemProductBinding
import com.nhatthanh.shopping.databinding.ItemSeeAllBinding
import com.nhatthanh.shopping.product._interface.GetPositionItem
import com.nhatthanh.shopping.product.activity.HomeActivity
import com.nhatthanh.shopping.product.model.Notifications
import com.nhatthanh.shopping.product.model.Product
import com.nhatthanh.shopping.product.model.SeeAll

class MainAdapterProduct(
    private val context: Context,
    private val listNotification: List<Notifications>,
    private val listSeeAll: List<SeeAll>,
    private val listProduct: List<Product>,
    private val getPositionItem: GetPositionItem
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class HorizontalViewHolder(binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val img = binding.banner
    }

    inner class GridViewHolder(binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val img = binding.imgProduct
        val tvPriceP = binding.tvPriceProduct
        val tvRateP = binding.tvRate
        val layout = binding.layoutProduct
        val tvName = binding.tvNameProduct
        val tvBrand = binding.tvBrandStoreProduct
        val tvPromotionP = binding.tvPromotion

    }

    inner class VerticalViewHolder(binding: ItemSeeAllBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvNewProduct = binding.tvProduct
        val tvSeeAll = binding.tvSeeAll
    }


    override fun getItemViewType(position: Int): Int {
        return when (position) {
            in 0 until listNotification.count() -> Utils.TYPE_NOTIFICATION
            in listNotification.count() until listNotification.count() + listSeeAll.count() -> Utils.TYPE_SEE_ALL
            else -> Utils.TYPE_PRODUCT
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            Utils.TYPE_NOTIFICATION -> return HorizontalViewHolder(
                ItemNotificationBinding.inflate(
                    LayoutInflater.from(context), parent, false
                )
            )
            Utils.TYPE_SEE_ALL -> return VerticalViewHolder(
                ItemSeeAllBinding.inflate(
                    LayoutInflater.from(
                        context
                    ), parent, false
                )
            )
            else -> return GridViewHolder(
                ItemProductBinding.inflate(
                    LayoutInflater.from(
                        context
                    ), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            Utils.TYPE_NOTIFICATION -> {
                val horizontalViewHolder: HorizontalViewHolder = holder as HorizontalViewHolder
                horizontalViewHolder.img.setImageResource(listNotification[position].imgNotification)
            }
            Utils.TYPE_SEE_ALL -> {
                val verticalViewHolder: VerticalViewHolder = holder as VerticalViewHolder
                with(verticalViewHolder) {
                    with(listSeeAll[position - listNotification.count()]) {
                        tvNewProduct.text = newProduct
                        tvSeeAll.text = seeAll
                    }
                }
            }
            else -> {
                val gridViewHolder: GridViewHolder = holder as GridViewHolder
                with(gridViewHolder) {
                    with(listProduct[position - (listSeeAll.count() + listNotification.count())]) {
                        img.setImageResource(image)
                        tvName.text = nameProduct
                        tvBrand.text = brandStore
                        tvPriceP.text = price.toString()
                        tvRateP.text = rate.toString()
                        tvPromotionP.text = position.toString()
                        layout.setOnClickListener {
                            getPositionItem.getItem(position)
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return (listNotification.size + listSeeAll.size + listProduct.size)
    }
}