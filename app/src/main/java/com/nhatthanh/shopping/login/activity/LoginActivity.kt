package com.nhatthanh.shopping.login.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nhatthanh.shopping.R
import com.nhatthanh.shopping.databinding.ActivityLoginBinding
import com.nhatthanh.shopping.login.fragment.WelcomeFragment

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.container, WelcomeFragment())
            .addToBackStack(null).commit()
    }
}