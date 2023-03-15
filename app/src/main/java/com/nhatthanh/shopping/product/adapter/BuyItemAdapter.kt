package com.nhatthanh.shopping.product.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nhatthanh.shopping.databinding.ItemCartSelectedBinding
import com.nhatthanh.shopping.product.listenerevent.GetIDItemCarSelected
import com.nhatthanh.shopping.product.model.Cart

@Suppress("DEPRECATION")
class BuyItemAdapter(
    private val context: Context,
    private val list: List<Cart>,
    private val getIDItemCarSelected: GetIDItemCarSelected
) :
    RecyclerView.Adapter<BuyItemAdapter.BuyViewHolder>() {

    private val listIdCartSelected = ArrayList<Int>()

    inner class BuyViewHolder(val binding: ItemCartSelectedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Cart) {
            with(binding) {
                with(item) {
                    Glide.with(context).load(imageCart).into(imgCartSelected)
                    tvNameCart.text = nameCart
                    tvPriceCart.text = (sumPrice * quantityItem).toString()
                    tvQuantityCart.text = "Quantity $quantityItem"
                    listIdCartSelected.add(list[adapterPosition].id)
                    getIDItemCarSelected.getListIDSelected(listIdCartSelected)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyViewHolder {
        return BuyViewHolder(
            ItemCartSelectedBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BuyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}