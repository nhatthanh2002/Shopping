package com.nhatthanh.shopping.login.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nhatthanh.shopping.R
import com.nhatthanh.shopping.Utils
import com.nhatthanh.shopping.product.activity.HomeActivity
import com.nhatthanh.shopping.databinding.FragmentWelcomeBinding
import com.nhatthanh.shopping.localData.AppDataBase
import com.nhatthanh.shopping.login.model.User
import com.nhatthanh.shopping.login.viewmodel.LoginViewModel

class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding
    private val loginViewModel: LoginViewModel by activityViewModels()
    private val sharedPreferences by lazy {
        activity?.getSharedPreferences(Utils.KEY_USER, Context.MODE_PRIVATE)
    }
//    private lateinit var dataBase: AppDataBase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        dataBase = AppDataBase.getDatabase(requireContext())!!
//        val user = dataBase.userDao().getUser()
        setUpUser()


        binding.btnSingIn.setOnClickListener {
            if (loginViewModel.checkRegister()) {
                if (loginViewModel.login(
                        binding.edYourEmail.text.toString(),
                        binding.edPassword.text.toString()
                    )
                ) {
                    saveUser(
                        binding.edYourEmail.text.toString(),
                        binding.edPassword.text.toString(), binding.rmbUser.isChecked
                    )
                    val intent = Intent(requireActivity(), HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(requireContext(), "Email or password wrong", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(requireContext(), "Bạn chưa có tài khoản", Toast.LENGTH_SHORT).show()
            }
        }
//        binding.tvSingUpLayout.tvSingUp.setOnClickListener {
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.replace(R.id.container, RegisterFragment())?.addToBackStack(null)?.commit()
//        }
        setUpData()
    }

    private fun saveUser(email: String, password: String, check: Boolean) {
        val editor = sharedPreferences?.edit()
        if (!check) {
            editor?.clear()
        } else {
            editor?.putString(Utils.EMAIL_USER, email)
            editor?.putString(Utils.PASSWORD_USER, password)
            editor?.putBoolean(Utils.REMEMBER_USER, check)
        }
        editor?.apply()
    }

    private fun setUpUser() {
        val email = sharedPreferences?.getString(Utils.EMAIL_USER, "")
        val password = sharedPreferences?.getString(Utils.PASSWORD_USER, "")
        val remember = sharedPreferences?.getBoolean(Utils.REMEMBER_USER, false)
        binding.edYourEmail.setText(email)
        binding.edPassword.setText(password)
        if (remember != null) {
            binding.rmbUser.isChecked = remember
        }

    }

    private fun setUpData() {
        loginViewModel.user.observe(viewLifecycleOwner) {
            binding.edYourEmail.setText(it.email)
            binding.edPassword.setText(it.password)
        }
    }

}