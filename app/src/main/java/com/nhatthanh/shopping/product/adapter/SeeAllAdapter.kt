package com.nhatthanh.shopping.product.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhatthanh.shopping.databinding.ItemSeeAllBinding
import com.nhatthanh.shopping.product.model.SeeAll

class SeeAllAdapter(private val list: List<SeeAll>) : RecyclerView.Adapter<SeeAllAdapter.SeeAllViewHolder>() {

    inner class SeeAllViewHolder(val binding: ItemSeeAllBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: SeeAll) {
            with(binding) {
                with(item) {
                    tvProduct.text = newProduct
                    tvSeeAll.text = seeAll
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeAllViewHolder {
        return SeeAllViewHolder(ItemSeeAllBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: SeeAllViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int =list.size
}