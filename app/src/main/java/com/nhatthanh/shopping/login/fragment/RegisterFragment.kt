package com.nhatthanh.shopping.login.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.nhatthanh.shopping.databinding.FragmentRegisterBinding
import com.nhatthanh.shopping.localData.AppDataBase
import com.nhatthanh.shopping.login.model.User
import com.nhatthanh.shopping.login.viewmodel.LoginViewModel


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private var fullName = ""
    private var email = ""
    private var phoneNumber = ""
    private var password = ""
    private var confirmPassword = ""
    private val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var dataBase: AppDataBase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        dataBase = AppDataBase.getDatabase(requireContext())!!
        binding.btnSingUp.setOnClickListener {
            if (validate()) {
                loginViewModel.setUser(addUser())
//                dataBase.userDao().insertUser(addUser())
                if (loginViewModel.checkRegister()) {
                    Toast.makeText(requireContext(), "Đăng kí thành công", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
//        binding.layoutSingIn.tvSingIn.setOnClickListener {
//            activity?.supportFragmentManager?.popBackStack()
//        }

    }

    private fun addUser(): User {
        return User(
            fullName = fullName,
            email = email,
            phoneNumber = phoneNumber,
            password = password
        )
    }

    private fun validate(): Boolean {
        fullName = binding.edFullName.text.toString()
        email = binding.edEmail.text.toString()
        phoneNumber = binding.edPhoneNumber.text.toString()
        password = binding.edPassword.text.toString()
        confirmPassword = binding.edConfirmPassword.text.toString()
        if (fullName.isEmpty()) {
            Toast.makeText(requireContext(), "Không để trống tên", Toast.LENGTH_SHORT).show()
            return false
        } else if (email.isEmpty()) {
            Toast.makeText(requireContext(), "Không để trống email tên", Toast.LENGTH_SHORT).show()
            return false
        } else if (phoneNumber.isEmpty()) {
            Toast.makeText(requireContext(), "Không để trống sđt tên", Toast.LENGTH_SHORT).show()
            return false
        } else if (password.isEmpty()) {
            Toast.makeText(requireContext(), "Không để trống password tên", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (confirmPassword.isEmpty()) {
            Toast.makeText(requireContext(), "Phải xác nhận lại mật khẩu tên", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (password != confirmPassword) {
            Toast.makeText(requireContext(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}