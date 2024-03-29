package com.nhatthanh.shopping.product.listenerevent

import com.nhatthanh.shopping.product.model.Cart

interface CartListener {
    fun listenUpdateCartItem(id: Int,check:Boolean)
    fun listenUpdateQuantityItem(id: Int,quantity:Int)
    fun addCart(id: Int, add: Int)
    fun subtractCart(id: Int, subtract: Int)
    fun deleteCart(id: Int)
    fun cartSelected(list: ArrayList<Cart>)
    fun listSelectedPosition(cart: Cart,position:Int)
}