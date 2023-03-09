package com.nhatthanh.shopping.navigtion.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhatthanh.shopping.navigtion.adapter.model.ItemNav
import com.nhatthanh.shopping.databinding.ItemNavBinding

class ItemNavAdapter(private val context: Context, private val list: List<ItemNav>) :
    RecyclerView.Adapter<ItemNavAdapter.MyViewHolder>() {
    private var itemSelected = 0

    inner class MyViewHolder(binding: ItemNavBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageSelected = binding.imgSelected
        val imageNav = binding.imgNav
        val titleNav = binding.titleNav
        val layoutNav = binding.layoutNav


         fun setSingleSelected(positionAdapter: Int) {
            if (positionAdapter == RecyclerView.NO_POSITION) return
            notifyItemChanged(itemSelected)
            itemSelected = positionAdapter
            notifyItemChanged(itemSelected)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemNavBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                imageNav.setImageResource(image)
                titleNav.text = title
                layoutNav.setOnClickListener {
                    setSingleSelected(adapterPosition)
                }
                if (itemSelected == position) {
                    imageSelected.visibility = View.VISIBLE
                    titleNav.setTextColor(Color.parseColor("#06AB8D"))
                    imageNav.setColorFilter(Color.parseColor("#06AB8D"))

                } else {
                    imageSelected.visibility = View.GONE
                    titleNav.setTextColor(Color.parseColor("#8B9E9E"))
                    imageNav.setColorFilter(Color.parseColor("#8B9E9E"))
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size
}