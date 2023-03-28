package com.nhatthanh.shopping.product.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nhatthanh.shopping.Utils
import com.nhatthanh.shopping.databinding.ItemCartBinding
import com.nhatthanh.shopping.product.listenerevent.CartListener
import com.nhatthanh.shopping.product.model.Cart

class DiffUtilCarAdapter(
    private val context: Context,
    private val cartListener: CartListener,
) :
    ListAdapter<Cart, DiffUtilCarAdapter.CarViewHolder>(DiffCallback()) {

    private val listCartSelected = ArrayList<Cart>()

    @Suppress("DEPRECATION")
    inner class CarViewHolder(
        private val binding: ItemCartBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Cart) {
            with(binding) {
                with(item) {
                    Glide.with(context).load(imageCart).fitCenter()
                        .into(imgProductCartItem)
                    tvNameProductCart.text = nameCart
                    tvPriceProductCart.text =
                        Utils.formatCurrency.format(sumPrice * quantityItem).toString()
                    tvQuantityItem.text = quantityItem.toString()
                    checkItem.isChecked = checkCart
                    checkItem.setOnClickListener {
                        if (checkItem.isChecked) {
                            cartListener.listenUpdateCartItem(id, true)
                            listCartSelected.add(item)
                        } else {
                            cartListener.listenUpdateCartItem(id, false)
                            listCartSelected.remove(item)
                        }
                        cartListener.cartSelected(listCartSelected)
                    }
                    btnDelete.setOnClickListener {
                        cartListener.deleteCart(id)
                    }
                    btnAddItem.setOnClickListener {
                        cartListener.addCart(id, quantityItem)
                        cartListener.listenUpdateQuantityItem(id, quantityItem)
                    }
                    btnSubtractItem.setOnClickListener {
                        cartListener.subtractCart(id, quantityItem)
                        cartListener.listenUpdateQuantityItem(id, quantityItem)
                    }
                }
            }
        }

        fun updateItem(bundle: Bundle) {
            with(binding) {
                with(bundle) {
                    if (containsKey("check")) {
                        checkItem.isChecked = getBoolean("check")
                    }
                    if (containsKey("quantity")) {
                        tvQuantityItem.text = getInt("quantity").toString()
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(
            ItemCartBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
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
        if (payloads.isEmpty() || payloads[0] !is Bundle) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val bundle = payloads[0] as Bundle
            holder.updateItem(bundle)
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
        if (oldItem.id == newItem.id) {
            return if (oldItem.checkCart == newItem.checkCart) {
                super.getChangePayload(oldItem, newItem)
            } else {
                val bundle = Bundle()
                if (oldItem.checkCart != newItem.checkCart) {
                    bundle.putBoolean("check", newItem.checkCart)
                }
                if (oldItem.quantityItem != newItem.quantityItem) {
                    bundle.putInt("quantity", newItem.quantityItem)
                }
                bundle
            }
        }
        return super.getChangePayload(oldItem, newItem)
    }

}