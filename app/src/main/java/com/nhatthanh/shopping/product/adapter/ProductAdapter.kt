package com.nhatthanh.shopping.product.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nhatthanh.shopping.databinding.ItemProductBinding
import com.nhatthanh.shopping.product.listenerevent.GetPositionItem
import com.nhatthanh.shopping.product.model.Products

class ProductAdapter(
    private val context: Context,
    private val list: List<Products>,
    private val getPositionItem: GetPositionItem
) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}