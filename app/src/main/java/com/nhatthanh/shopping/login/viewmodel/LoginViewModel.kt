package com.nhatthanh.shopping.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nhatthanh.shopping.login.model.User

class LoginViewModel : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun setUser(user: User) {
        _user.value = user
    }


    fun login(email: String, password: String): Boolean {
        return user.value!!.email == email && user.value!!.password == password
    }

    fun checkRegister(): Boolean {
        return user.value != null
    }
}