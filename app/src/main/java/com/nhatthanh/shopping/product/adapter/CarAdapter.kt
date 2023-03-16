package com.nhatthanh.shopping.product.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nhatthanh.shopping.Utils
import com.nhatthanh.shopping.databinding.ItemCartBinding
import com.nhatthanh.shopping.product.listenerevent.CartListener
import com.nhatthanh.shopping.product.model.Cart

@Suppress("DEPRECATION")
class CarAdapter(
    private val context: Context,
    private val list: List<Cart>,
    private val cartListener: CartListener
) :
    RecyclerView.Adapter<CarAdapter.MyViewHolder>() {

    private val listCartSelected = ArrayList<Cart>()

    inner class MyViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Cart) {
            with(binding) {
                with(item) {
                    Glide.with(context).load(imageCart).centerCrop().override(80, 80)
                        .into(imgProductCartItem)
                    tvNameProductCart.text = nameCart
                    tvPriceProductCart.text =
                        Utils.formatCurrency.format(sumPrice * quantityItem).toString()
                    tvQuantityItem.text = quantityItem.toString()
                    btnDelete.setOnClickListener { cartListener.deleteCart(id) }
                    checkItem.setOnClickListener {
                        if (checkItem.isChecked) {
                            listCartSelected.add(item)
                            cartListener.setCheckItem(item, id)
                            checkItem.isChecked=true
                        } else {
                            listCartSelected.remove(item)
                            cartListener.setCheckItem(item, id)
                            checkItem.isChecked=false
                        }
                        cartListener.cartSelected(listCartSelected)
                    }

                    btnAddItem.setOnClickListener {
                        cartListener.addCart(id, quantityItem)
                    }
                    btnSubtractItem.setOnClickListener {
                        cartListener.subtractCart(
                            id,
                            quantityItem
                        )
                    }

                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemCartBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}