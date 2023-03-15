package com.nhatthanh.shopping.product.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.nhatthanh.shopping.R
import com.nhatthanh.shopping.Utils
import com.nhatthanh.shopping.callapi.RetrofitInstance
import com.nhatthanh.shopping.databinding.FragmentHomeBinding
import com.nhatthanh.shopping.product.activity.HomeActivity
import com.nhatthanh.shopping.product.adapter.HomeSpanSizeLookUp
import com.nhatthanh.shopping.product.listenerevent.GetPositionItem
import com.nhatthanh.shopping.product.adapter.MainAdapterProduct
import com.nhatthanh.shopping.product.adapter.NotificationAdapter
import com.nhatthanh.shopping.product.model.Notifications
import com.nhatthanh.shopping.product.model.Products
import com.nhatthanh.shopping.product.viewmodel.ProductViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment(), GetPositionItem {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeActivity: HomeActivity
    private val productViewModel: ProductViewModel by activityViewModels()
    private lateinit var list: ArrayList<Any>
    var data: Bundle? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeActivity = activity as HomeActivity
        homeActivity.checkFragmentOnActivity()
        getListProduct()
        getListNotification()
        setUpList()
    }


    private fun getListProduct() {
        RetrofitInstance.apiInterface.getListProduct().enqueue(object : Callback<List<Products>?> {
            override fun onResponse(
                call: Call<List<Products>?>,
                response: Response<List<Products>?>
            ) {
                val listProduct = response.body()
                listProduct?.let { productViewModel.getListProduct(it) }
            }

            override fun onFailure(call: Call<List<Products>?>, t: Throwable) {
                Log.e("Error Api", "${t.message}")
            }
        })
    }

    private fun getListNotification() {
        RetrofitInstance.apiInterface.getListNotification()
            .enqueue(object : Callback<List<Notifications>?> {
                override fun onResponse(
                    call: Call<List<Notifications>?>,
                    response: Response<List<Notifications>?>
                ) {
                    val listNotification = response.body()
                    listNotification?.let { productViewModel.getListNotifications(it) }
                }

                override fun onFailure(call: Call<List<Notifications>?>, t: Throwable) {
                    Log.e("Error Api", "${t.message}")
                }
            })
    }

    private fun setUpList() {
        productViewModel.apply {
            listNotifications.observe(viewLifecycleOwner) { listNotifications ->
                listSeeAll.observe(viewLifecycleOwner) { listSeeAll ->
                    listProduct.observe(viewLifecycleOwner) { listProduct ->
                        binding.rvNotification.apply {
                            setHasFixedSize(true)
                            adapter = NotificationAdapter(requireContext(), listNotifications)
                        }

                        list = ArrayList()
                        // list.addAll(listNotifications)
                        list.addAll(listSeeAll)
                        list.addAll(listProduct)
                        binding.rvProduct.apply {
                            setHasFixedSize(true)
                            val mainAdapter =
                                MainAdapterProduct(
                                    requireContext(),
                                    list,
                                    this@HomeFragment
                                )
                            adapter = mainAdapter
                            val gridLayoutManager = GridLayoutManager(
                                requireContext(),
                                2,
                                GridLayoutManager.VERTICAL,
                                false
                            )
                            gridLayoutManager.spanSizeLookup = HomeSpanSizeLookUp(mainAdapter)
                            layoutManager = gridLayoutManager

                        }
                    }
                }
            }
        }
    }

//    fun getListBanner(): List<Notifications> {
//        productViewModel.listNotifications.observe(viewLifecycleOwner) { listNotifications ->
//            listBanner = ArrayList()
//            listBanner.addAll(listNotifications)
//        }
//        return listBanner
//    }

    override fun getItem(id: Int) {
        Log.e("ID_Product", "$id")
        val detailsFragment = DetailsFragment()
        val bundle = Bundle()
        bundle.putInt(Utils.ID_PRODUCT, id)
        detailsFragment.arguments = bundle
        replaceFragment(detailsFragment)
    }

    private fun replaceFragment(fragment: Fragment) {

        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_current, fragment)?.addToBackStack(null)?.commit()
    }
}