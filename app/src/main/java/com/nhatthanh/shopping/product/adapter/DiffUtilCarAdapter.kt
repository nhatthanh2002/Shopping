package com.nhatthanh.shopping.product.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nhatthanh.shopping.Utils
import com.nhatthanh.shopping.databinding.ItemCartBinding
import com.nhatthanh.shopping.product.model.Cart

class DiffUtilCarAdapter(
    private val context: Context,
    private val checkCartItem: (Int, Boolean) -> Unit
) :
    ListAdapter<Cart, DiffUtilCarAdapter.CarViewHolder>(DiffCallback()) {


    inner class CarViewHolder(
        private val binding: ItemCartBinding,
        private val checkCartItem: (Int, Boolean) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        lateinit var cart: Cart

        init {
            binding.checkItem.setOnClickListener { checkCartItem(cart.id, !it.isSelected) }
        }

        fun bind(item: Cart) {
            with(binding) {
                with(item) {
                    Glide.with(context).load(imageCart).centerCrop().override(80, 80)
                        .into(imgProductCartItem)
                    tvNameProductCart.text = nameCart
                    tvPriceProductCart.text =
                        Utils.formatCurrency.format(sumPrice * quantityItem).toString()
                    tvQuantityItem.text = quantityItem.toString()
                    checkItem.setOnClickListener { checkCartItem(id, !it.isSelected) }
                }
            }
        }

        fun bindCheckCartItem(check: Boolean) {
            binding.checkItem.isChecked = check
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(
            ItemCartBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ), checkCartItem
        )
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: CarViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            if (payloads[0] == true) {
                holder.bindCheckCartItem(getItem(position).checkCart)
            }
        }

    }
}

class DiffCallback : DiffUtil.ItemCallback<Cart>() {
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