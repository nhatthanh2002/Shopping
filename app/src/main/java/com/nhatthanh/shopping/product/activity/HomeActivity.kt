package com.nhatthanh.shopping.product.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.nhatthanh.shopping.R
import com.nhatthanh.shopping.Utils
import com.nhatthanh.shopping.navigtion.adapter.ItemNavAdapter
import com.nhatthanh.shopping.databinding.ActivityHomeBinding
import com.nhatthanh.shopping.localData.application.CartApplication
import com.nhatthanh.shopping.navigtion.adapter.model.ItemNav
import com.nhatthanh.shopping.product.fragment.*
import com.nhatthanh.shopping.product.viewmodel.CartMolderFactory
import com.nhatthanh.shopping.product.viewmodel.CartViewModel
import com.nhatthanh.shopping.product.viewmodel.ProductViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpDrawer()
        openCart()
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        0 -> replaceFragment(HomeFragment())
                        1 -> replaceFragment(FeedsFragment())
                        2 -> replaceFragment(TransactionFragment())
                        3 -> replaceFragment(MyProfileFragment())
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

    }

    private fun openDetailProduct() {
        if (intent.action.equals("open_detail_product")) {
            binding.containerDetailFragment.visibility = View.VISIBLE
            replaceFragment2(DetailsFragment())
        }
    }

    private fun openCart() {
        binding.cart.setOnClickListener {
            binding.containerDetailFragment.visibility = View.VISIBLE
            replaceFragment2(YourCartFragment())
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_current, fragment).addToBackStack(null).commit()
    }

    private fun replaceFragment2(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_detail_fragment, fragment).addToBackStack(null).commit()
    }

    private fun setUpDrawer() {
        binding.imgOpenMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(
                GravityCompat.START
            )
        }

    }
}