package com.nhatthanh.shopping.login.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.nhatthanh.shopping.localData.repository.UserRepository
import com.nhatthanh.shopping.login.model.User
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    val allUser: LiveData<List<User>> = userRepository.allUser.asLiveData()

    fun insertUser(user: User) = viewModelScope.launch {
        userRepository.insertUser(user)
    }


    fun login(email: String, password: String): Boolean {
        for (i in allUser.value!!) {
            return i.email == email && i.password == password
        }
        return false
    }

    fun checkRegister(): Boolean {
        return allUser.value != null
    }

}

class LoginMolderFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}