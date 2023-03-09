package com.nhatthanh.shopping.product.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nhatthanh.shopping.product.fragment.FeedsFragment
import com.nhatthanh.shopping.product.fragment.HomeFragment
import com.nhatthanh.shopping.product.fragment.MyProfileFragment
import com.nhatthanh.shopping.product.fragment.TransactionFragment

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> FeedsFragment()
            2 -> TransactionFragment()
            else -> MyProfileFragment()
        }
    }
}