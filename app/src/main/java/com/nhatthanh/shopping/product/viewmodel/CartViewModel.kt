package com.nhatthanh.shopping.product.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.nhatthanh.shopping.localData.repository.CartRepository
import com.nhatthanh.shopping.product.model.Cart
import kotlinx.coroutines.launch

class CartViewModel(private val repository: CartRepository) : ViewModel() {
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private val _checkItem = MutableLiveData<Cart>()
    val checkItem: LiveData<Cart> = _checkItem

    private val _totalPriceItemCart = MutableLiveData<Double>()
    val totalPriceItemCart: LiveData<Double> = _totalPriceItemCart

    private val _quantityCartSelected = MutableLiveData<Int>()
    val quantityCarSelected: LiveData<Int> = _quantityCartSelected

    private val _listCartSelected = MutableLiveData<List<Cart>>()
    val listCartSelected: LiveData<List<Cart>>
        get() = _listCartSelected

    private val _listIdCart = MutableLiveData<List<Int>>()
    val listIdCart: LiveData<List<Int>> = _listIdCart

    init {
        _quantity.value = 1
    }

    fun setTotalPriceCart(total: Double): MutableLiveData<Double> {
        _totalPriceItemCart.value = total
        return _totalPriceItemCart
    }

    fun setCheckedItem(cart: Cart): MutableLiveData<Cart> {
        _checkItem.value = cart
        return _checkItem
    }

    fun addItem() {
        _quantity.value = (_quantity.value)?.plus(1)
    }

    fun subtractItem() {
        _quantity.value = (_quantity.value)?.minus(1)
    }

    val listCart: LiveData<List<Cart>> = repository.allCart.asLiveData()

    fun insertCar(cart: Cart) = viewModelScope.launch {
        repository.insertCart(cart)
    }

    fun deleteCart(id: Int) = viewModelScope.launch {
        repository.deleteCart(id)
    }

    fun updateQuantity(id: Int, quantity: Int) = viewModelScope.launch {
        repository.updateQuantity(id, quantity)
    }

    fun updateCartSelected(id: Int, check: Boolean) = viewModelScope.launch {
        repository.updateCartSelected(id, check)
    }

    fun getListCartSelected(list: List<Cart>): MutableLiveData<List<Cart>> {
        _listCartSelected.value = list
        return _listCartSelected
    }

    fun setItemSelected(list: List<Cart>): MutableLiveData<Int> {
        _quantityCartSelected.value = list.size
        return _quantityCartSelected
    }

    fun getListIDCart(list: List<Int>): MutableLiveData<List<Int>> {
        _listIdCart.value = list
        return _listIdCart
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