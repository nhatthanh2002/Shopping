package com.nhatthanh.shopping.product.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.nhatthanh.shopping.localData.repository.CartRepository
import com.nhatthanh.shopping.login.model.Cart
import com.nhatthanh.shopping.product.model.Product
import kotlinx.coroutines.launch

class CartViewModel(private val repository: CartRepository) : ViewModel() {
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int>
        get() = _quantity

    val listCart: LiveData<List<Cart>> = repository.allCart.asLiveData()

    fun insertCar(cart: Cart) = viewModelScope.launch {
        repository.insertCart(cart)
    }
}

class CartMolderFactory(private val repository: CartRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}