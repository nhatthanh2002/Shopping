package com.nhatthanh.shopping.product.adapter

import androidx.recyclerview.widget.GridLayoutManager
import com.nhatthanh.shopping.Utils

class HomeSpanSizeLookUp(private val adapterProduct: MainAdapterProduct) :
    GridLayoutManager.SpanSizeLookup() {
    override fun getSpanSize(position: Int): Int {
        return when (adapterProduct.getItemViewType(position)) {
//            Utils.TYPE_NOTIFICATION -> 2
            Utils.TYPE_SEE_ALL -> 2
            else -> 1
        }
    }
}