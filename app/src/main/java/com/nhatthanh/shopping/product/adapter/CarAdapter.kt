package com.nhatthanh.shopping.product.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhatthanh.shopping.databinding.ItemCartBinding
import com.nhatthanh.shopping.login.model.Cart

class CarAdapter(private val context: Context, private val list: List<Cart>) :
    RecyclerView.Adapter<CarAdapter.MyViewHolder>() {
    inner class MyViewHolder(binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        val checkItemCart = binding.checkItem
        val imgCart = binding.imgProductCartItem
        val nameCart = binding.tvNameProductCart
        val sum = binding.tvPriceProductCart
        val quantity = binding.tvQuantityItem
        val add = binding.btnAddItem
        val subtract = binding.btnSubtractItem
        val delete = binding.btnDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemCartBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                checkItemCart.isChecked = checkItem
                imgCart.setImageResource(productItem.image)
                nameCart.text = productItem.nameProduct
                sum.text = (productItem.price * quantityItem).toString()
                quantity.text = quantityItem.toString()
                add.setOnClickListener {

                }
                subtract.setOnClickListener { }
                delete.setOnClickListener { }
            }
        }
    }

    override fun getItemCount(): Int = list.size
}