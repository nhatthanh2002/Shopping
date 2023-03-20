package com.nhatthanh.shopping.product.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.nhatthanh.shopping.Utils
import com.nhatthanh.shopping.localData.repository.CartRepository
import com.nhatthanh.shopping.product.model.Cart
import kotlinx.coroutines.launch

class CartViewModel(private val repository: CartRepository) : ViewModel() {
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private val _listCart = MutableLiveData<List<Cart>>()
    val listCart: LiveData<List<Cart>> = _listCart

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

    init {
        viewModelScope.launch {
            repository.allCart.collect { list ->
                _listCart.postValue(list)
            }
        }
    }

    fun getTotalPriceCart(): String {
        var total = 0.0
        for (item in _listCartSelected.value!!) {
            total += (item.quantityItem * item.sumPrice)
        }
        return Utils.formatCurrency.format(total).toString()
    }


    fun setCheckCartAll(checkAll: Boolean) {
        _listCart.value = _listCart.value?.map {
            it.copy(checkCart = checkAll)
        }
    }

    fun setCheckItemCart(id: Int, selected: Boolean) {
        _listCart.value = _listCart.value?.map {
            if (it.id == id) {
                it.copy(checkCart = selected)
            } else {
                it
            }
        }
    }

    fun addItem() {
        _quantity.value = (_quantity.value)?.plus(1)
    }

    fun subtractItem() {
        _quantity.value = (_quantity.value)?.minus(1)
    }


    fun insertCar(cart: Cart) = viewModelScope.launch {
        repository.insertCart(cart)
    }

    fun deleteCart(id: Int) = viewModelScope.launch {
        repository.deleteCart(id)
    }

    fun updateQuantity(id: Int, quantity: Int) = viewModelScope.launch {
        repository.updateQuantity(id, quantity)
    }

    fun setListCartSelected(list: List<Cart>) {
        _listCartSelected.value = list
    }


    fun getQuantityItemSelected():String {
        var quantity=0
        quantity = _listCartSelected.value!!.size
        return "Buy ($quantity item)"
    }

    fun setListIDCart(list: List<Int>) {
        _listIdCart.value = list
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