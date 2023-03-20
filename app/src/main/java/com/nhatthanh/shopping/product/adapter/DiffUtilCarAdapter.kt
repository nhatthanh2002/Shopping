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
import com.nhatthanh.shopping.product.listenerevent.CartListener
import com.nhatthanh.shopping.product.model.Cart

class DiffUtilCarAdapter(
    private val context: Context,
    private val cartListener: CartListener,
) :
    ListAdapter<Cart, DiffUtilCarAdapter.CarViewHolder>(DiffCallback()) {

    private val listCartSelected = ArrayList<Cart>()

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
                            cartListener.listenUpdateCartItem(id, checkItem.isChecked)
                            listCartSelected.add(item)
                        } else {
                            cartListener.listenUpdateCartItem(id, checkItem.isChecked)
                            listCartSelected.remove(item)
                        }
                        cartListener.cartSelected(listCartSelected)
                    }
                    btnDelete.setOnClickListener {
                        listCartSelected.remove(item)
                        cartListener.deleteCart(id)
                    }
                    btnAddItem.setOnClickListener {
                        cartListener.addCart(id, quantityItem)
                    }
                    btnSubtractItem.setOnClickListener {
                        cartListener.subtractCart(id, quantityItem)
                    }
                }
            }
        }

        fun bindCheckCartItem(item: Cart) {
            binding.checkItem.isChecked = item.checkCart
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
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            if (payloads[0] == true) {
                holder.bindCheckCartItem(getItem(position))
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