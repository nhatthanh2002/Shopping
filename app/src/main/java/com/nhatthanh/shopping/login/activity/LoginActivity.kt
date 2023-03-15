package com.nhatthanh.shopping.login.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nhatthanh.shopping.R
import com.nhatthanh.shopping.Utils
import com.nhatthanh.shopping.databinding.ActivityLoginBinding
import com.nhatthanh.shopping.login.fragment.LoginFragment
import com.nhatthanh.shopping.product.activity.HomeActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val sharedPreferences by lazy {
        getSharedPreferences(Utils.KEY_USER, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.container, LoginFragment())
            .addToBackStack(null).commit()
        if (sharedPreferences!=null){
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
    }
}