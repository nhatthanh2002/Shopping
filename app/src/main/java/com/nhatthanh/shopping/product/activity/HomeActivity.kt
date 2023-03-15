package com.nhatthanh.shopping.product.activity

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.nhatthanh.shopping.R
import com.nhatthanh.shopping.databinding.ActivityHomeBinding
import com.nhatthanh.shopping.localData.application.MainApplication
import com.nhatthanh.shopping.product.fragment.*
import com.nhatthanh.shopping.product.viewmodel.CartMolderFactory
import com.nhatthanh.shopping.product.viewmodel.CartViewModel


@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val cartViewModel: CartViewModel by viewModels {
        CartMolderFactory((application as MainApplication).cartRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpDrawer()
        openCart()
        checkFragmentOnActivity()


        cartViewModel.listCart.observe(this) {
            binding.tvStatusQuantity.text = it.size.toString()
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        HOME_FRAGMENT_INDEX -> {
                            replaceFragment(HomeFragment())
                            binding.titleToolBar.setText(R.string.Home)
                        }
                        FEEDS_FRAGMENT_INDEX -> {
                            replaceFragment(FeedsFragment())
                            binding.titleToolBar.setText(R.string.Feeds)
                        }
                        TRANSACTION_FRAGMENT_INDEX -> {
                            replaceFragment(TransactionFragment())
                            binding.titleToolBar.setText(R.string.Transaction)
                        }
                        MY_PROFILE_FRAGMENT -> {
                            replaceFragment(MyProfileFragment())
                            binding.titleToolBar.setText(R.string.My_profile)
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

    }

    private fun signalOpenDialog() {

    }

    fun checkFragmentOnActivity() {
        val window = window
        when (supportFragmentManager.findFragmentById(R.id.fragment_current)) {
            is HomeFragment -> {
                with(binding) {
                    tabLayout.visibility = View.VISIBLE
                    toolBar.visibility = View.VISIBLE
                    dotRed.visibility = View.VISIBLE
                    tvStatusQuantity.visibility = View.VISIBLE
                    imgOpenMenu.visibility = View.VISIBLE
                    titleToolBar.visibility = View.VISIBLE
                    cart.visibility = View.VISIBLE
                    with(window) {
                        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                        statusBarColor =
                            ContextCompat.getColor(this@HomeActivity, R.color.background)
                    }
                }
            }
            else -> {
                with(binding) {
                    tabLayout.visibility = View.GONE
                    toolBar.visibility = View.GONE
                    dotRed.visibility = View.GONE
                    tvStatusQuantity.visibility = View.GONE
                    imgOpenMenu.visibility = View.GONE
                    titleToolBar.visibility = View.GONE
                    cart.visibility = View.GONE
                    with(window) {
                        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                        statusBarColor =
                            ContextCompat.getColor(this@HomeActivity, R.color.white)
                    }
                }
            }
        }
    }

    @SuppressLint("InternalInsetResource", "DiscouragedApi")
    private fun getStatusBarHeight(): Int {
        val height: Int
        val myResources: Resources = resources
        val idStatusBarHeight: Int =
            myResources.getIdentifier("status_bar_height", "dimen", "android")
        if (idStatusBarHeight > 0) {
            height = resources.getDimensionPixelSize(idStatusBarHeight)
            Toast.makeText(this, "Status Bar Height = $height", Toast.LENGTH_LONG).show()
        } else {
            height = 0
            Toast.makeText(this, "Resources NOT found", Toast.LENGTH_LONG).show()
        }
        return height
    }


    private fun openCart() {
        binding.cart.setOnClickListener {
            replaceFragment(YourCartFragment())
        }
//        val layoutParams=binding.imgOpenMenu.layoutParams
//        layoutParams.height=80
//        layoutParams.width=80
//        binding.imgOpenMenu.layoutParams=layoutParams
    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_current, fragment).addToBackStack(null).commit()
    }


    private fun setUpDrawer() {
        binding.imgOpenMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(
                GravityCompat.START
            )
        }

    }

    companion object {
        const val HOME_FRAGMENT_INDEX = 0
        const val FEEDS_FRAGMENT_INDEX = 1
        const val TRANSACTION_FRAGMENT_INDEX = 2
        const val MY_PROFILE_FRAGMENT = 3
    }
}