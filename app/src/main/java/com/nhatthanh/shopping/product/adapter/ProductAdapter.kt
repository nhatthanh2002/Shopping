package com.nhatthanh.shopping.product.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhatthanh.shopping.databinding.ItemProductBinding
import com.nhatthanh.shopping.product.model.Product

class ProductAdapter(
    private val context: Context,
    private val list: List<Product>
) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    inner class MyViewHolder(binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.tvNameProduct
        val brand = binding.tvBrandStoreProduct
        val promotionP = binding.tvPromotion
        val img = binding.imgProduct
        val priceP = binding.tvPriceProduct
        val rateP = binding.tvRate
        val layout = binding.layoutProduct

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemProductBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                name.text = nameProduct
                brand.text = brandStore
                priceP.text = price.toString()
                rateP.text = rate.toString()
                promotionP.text = promotion.toString()
                img.setImageResource(image)
                layout.setOnClickListener {

                }
            }
        }
    }

    override fun getItemCount(): Int = list.size
}