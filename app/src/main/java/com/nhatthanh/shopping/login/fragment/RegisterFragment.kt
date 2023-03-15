package com.nhatthanh.shopping.login.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.nhatthanh.shopping.databinding.FragmentRegisterBinding
import com.nhatthanh.shopping.localData.application.MainApplication
import com.nhatthanh.shopping.login.model.User
import com.nhatthanh.shopping.login.viewmodel.LoginMolderFactory
import com.nhatthanh.shopping.login.viewmodel.LoginViewModel


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private var fullName = ""
    private var email = ""
    private var phoneNumber = ""
    private var password = ""
    private var confirmPassword = ""

    private val loginViewModel: LoginViewModel by activityViewModels {
        LoginMolderFactory((requireActivity().application as MainApplication).userRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSingUp.setOnClickListener {
            if (validate()) {
                loginViewModel.insertUser(addUser())
                loginViewModel.allUser.observe(viewLifecycleOwner) {
                    if (loginViewModel.checkRegister()) {
                        Toast.makeText(requireContext(), "Register success", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(requireContext(), "Register failure", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
        binding.layoutSingIn.tvSingIn.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        loginViewModel.allUser.observe(viewLifecycleOwner) { list ->
            for (i in list) {
                Log.e("User_Item", i.email)
            }
        }
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