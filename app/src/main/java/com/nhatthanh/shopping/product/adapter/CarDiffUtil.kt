package com.nhatthanh.shopping.product.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nhatthanh.shopping.product.model.Cart

class CarDiffUtil(private val oldList: List<Cart>, private val newList: List<Cart>) :
    DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return newList.size
    }

    override fun getNewListSize(): Int {
        return oldList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[newItemPosition].id == newList[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}