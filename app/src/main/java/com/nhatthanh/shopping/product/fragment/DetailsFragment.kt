package com.nhatthanh.shopping.product.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.nhatthanh.shopping.R
import com.nhatthanh.shopping.databinding.FragmentDetailsBinding
import com.nhatthanh.shopping.product.adapter.DetailViewPagerAdapter
import com.nhatthanh.shopping.product.viewmodel.ProductViewModel

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val productViewModel: ProductViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabLayout()

        binding.btnAddCart.setOnClickListener {

        }
        binding.back.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun setUpTabLayout() {
        val adapter = activity?.supportFragmentManager?.let {
            DetailViewPagerAdapter(
                it,
                lifecycle
            )
        }
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Details"
                1 -> tab.text = "Review"
            }
        }.attach()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
               when(position){
                   0->binding.btnAdd.setImageResource(R.drawable.add)
                   1->binding.btnAdd.setImageResource(R.drawable.add_color)
                   else->binding.btnAdd.setImageResource(R.drawable.add)
               }
                super.onPageSelected(position)
            }
        })

    }

}