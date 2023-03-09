package com.nhatthanh.shopping.product.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhatthanh.shopping.databinding.ItemColorBinding
import com.nhatthanh.shopping.product.model.Color

class ColorAdapter(private val context: Context, private val list: List<Color>) :
    RecyclerView.Adapter<ColorAdapter.MyViewHolder>() {
    inner class MyViewHolder(binding: ItemColorBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvColor = binding.tvColor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemColorBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                tvColor.text = color
            }
        }
    }

    override fun getItemCount(): Int = list.size
}