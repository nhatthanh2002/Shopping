package com.nhatthanh.shopping.product.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nhatthanh.shopping.product.model.Cart

class CarDiffUtil : DiffUtil.ItemCallback<Cart>() {
    override fun areItemsTheSame(oldItem: Cart, newItem: Cart): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cart, newItem: Cart): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: Cart, newItem: Cart): Any? {
        return if (oldItem.checkCart != newItem.checkCart) true else null
    }
}