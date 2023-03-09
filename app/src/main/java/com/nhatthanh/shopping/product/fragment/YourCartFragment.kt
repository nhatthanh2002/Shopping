package com.nhatthanh.shopping.product.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nhatthanh.shopping.R
import com.nhatthanh.shopping.databinding.FragmentYourCartBinding
import com.nhatthanh.shopping.localData.application.CartApplication
import com.nhatthanh.shopping.product.adapter.CarAdapter
import com.nhatthanh.shopping.product.viewmodel.CartMolderFactory
import com.nhatthanh.shopping.product.viewmodel.CartViewModel

class YourCartFragment : Fragment() {
    private lateinit var binding: FragmentYourCartBinding
    private val cartViewModel: CartViewModel by activityViewModels {
        CartMolderFactory((requireActivity().application as CartApplication).cartRepository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYourCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        cartViewModel.listCart.observe(viewLifecycleOwner){listCart->
            binding.rvCart.setHasFixedSize(true)
            binding.rvCart.layoutManager=LinearLayoutManager(requireContext())
            binding.rvCart.adapter=CarAdapter(requireContext(),listCart)
        }


    }


}