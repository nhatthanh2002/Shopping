package com.nhatthanh.shopping.product.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhatthanh.shopping.databinding.ItemSeeAllBinding
import com.nhatthanh.shopping.product.model.SeeAll

class SeeAllAdapter(val context: Context, val list: List<SeeAll>) :
    RecyclerView.Adapter<SeeAllAdapter.MyViewHolder>() {
    inner class MyViewHolder(binding: ItemSeeAllBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvNewProduct = binding.tvProduct
        val tvSeeAll = binding.tvSeeAll
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemSeeAllBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            with(list[position]){
                tvNewProduct.text=newProduct
                tvSeeAll.text=seeAll
            }
        }
    }

    override fun getItemCount(): Int = list.size
}