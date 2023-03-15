package com.nhatthanh.shopping.product.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.nhatthanh.shopping.databinding.FragmentDetailBinding
import com.nhatthanh.shopping.product.viewmodel.ProductViewModel

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val productViewModel: ProductViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productViewModel.productDetail.observe(viewLifecycleOwner) {
            binding.tvDescription.text = it.description
        }
    }

}