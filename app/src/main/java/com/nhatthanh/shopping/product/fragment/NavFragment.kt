package com.nhatthanh.shopping.product.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nhatthanh.shopping.R
import com.nhatthanh.shopping.Utils
import com.nhatthanh.shopping.navigtion.adapter.ItemNavAdapter
import com.nhatthanh.shopping.databinding.FragmentNavBinding
import com.nhatthanh.shopping.navigtion.adapter.model.ItemNav
import com.nhatthanh.shopping.product.activity.HomeActivity


class NavFragment : Fragment() {
    private lateinit var binding: FragmentNavBinding
    lateinit var adapter: ItemNavAdapter
    private lateinit var listItemNav: ArrayList<ItemNav>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgCloseDrawer.setOnClickListener {
            val intent = Intent(requireContext(), HomeActivity::class.java)
            startActivity(intent)
        }
        binding.logout.setOnClickListener {
           val sharedPreferences=activity?.getSharedPreferences(Utils.KEY_USER,Context.MODE_PRIVATE)
            val editor:SharedPreferences.Editor= sharedPreferences?.edit()!!
            editor.clear()?.apply()
            requireActivity().finishAffinity()
        }

        setUpItemNav()

    }


    @JvmName("getListItemNav1")
    fun getListItemNav(): ArrayList<ItemNav> {
        val listItem = ArrayList<ItemNav>()
        listItem.add(ItemNav(R.drawable.dashboard, "Dashboard"))
        listItem.add(ItemNav(R.drawable.become_seller, "Become Seller"))
        listItem.add(ItemNav(R.drawable.help_center, "Help Center"))
        listItem.add(ItemNav(R.drawable.all_products, "All Products"))
        listItem.add(ItemNav(R.drawable.new_release, "New Release"))
        listItem.add(ItemNav(R.drawable.promotion, "Promotion"))
        listItem.add(ItemNav(R.drawable.settings, "Settings"))
        return listItem
    }

    private fun setUpItemNav() {
        listItemNav = getListItemNav()
        adapter = ItemNavAdapter(requireContext(), listItemNav)
        binding.rvNav.setHasFixedSize(true)
        binding.rvNav.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNav.adapter = adapter
    }

}