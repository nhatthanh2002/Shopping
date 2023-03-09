package com.nhatthanh.shopping.product.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.nhatthanh.shopping.R
import com.nhatthanh.shopping.databinding.FragmentHomeBinding
import com.nhatthanh.shopping.product._interface.GetPositionItem
import com.nhatthanh.shopping.product.adapter.MainAdapterProduct
import com.nhatthanh.shopping.product.model.Color
import com.nhatthanh.shopping.product.model.Product
import com.nhatthanh.shopping.product.model.SeeAll
import com.nhatthanh.shopping.product.viewmodel.ProductViewModel

class HomeFragment : Fragment(), GetPositionItem {
    private lateinit var binding: FragmentHomeBinding
    private val productViewModel: ProductViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpList()
    }


    private fun setUpList() {
        productViewModel.apply {
            listNotifications.observe(viewLifecycleOwner) { listNotifications ->
                listSeeAll.observe(viewLifecycleOwner) { listSeeAll ->
                    listProduct.observe(viewLifecycleOwner) { listProduct ->
                        binding.rvProduct.adapter = MainAdapterProduct(
                            requireContext(),
                            listNotifications,
                            listSeeAll,
                            listProduct,
                            this@HomeFragment
                        )
                        binding.rvProduct.layoutManager = LinearLayoutManager(requireContext())
                    }
                }
            }
        }
    }

    override fun getItem(position: Int) {
        productViewModel.setPosition(position)
        replaceFragment(DetailsFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_current, fragment)?.addToBackStack(null)?.commit()
    }


}