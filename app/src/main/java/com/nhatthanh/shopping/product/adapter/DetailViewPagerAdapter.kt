package com.nhatthanh.shopping.product.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nhatthanh.shopping.product.fragment.DetailFragment
import com.nhatthanh.shopping.product.fragment.ReviewFragment

class DetailViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DetailFragment()
            1 -> ReviewFragment()
            else -> Fragment()
        }
    }
}