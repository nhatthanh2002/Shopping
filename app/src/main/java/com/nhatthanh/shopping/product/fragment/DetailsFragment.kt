package com.nhatthanh.shopping.product.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.nhatthanh.shopping.R
import com.nhatthanh.shopping.Utils
import com.nhatthanh.shopping.callapi.RetrofitInstance
import com.nhatthanh.shopping.databinding.FragmentDetailsBinding
import com.nhatthanh.shopping.localData.application.MainApplication
import com.nhatthanh.shopping.product.activity.HomeActivity
import com.nhatthanh.shopping.product.adapter.DetailViewPagerAdapter
import com.nhatthanh.shopping.product.model.Cart
import com.nhatthanh.shopping.product.model.ProductDetail
import com.nhatthanh.shopping.product.viewmodel.CartMolderFactory
import com.nhatthanh.shopping.product.viewmodel.CartViewModel
import com.nhatthanh.shopping.product.viewmodel.ProductViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsFragment : Fragment() {
    private lateinit var homeActivity: HomeActivity
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var cart: Cart
    private val productViewModel: ProductViewModel by activityViewModels()
    private val cartViewModel: CartViewModel by activityViewModels {
        CartMolderFactory((requireActivity().application as MainApplication).cartRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeActivity = activity as HomeActivity
        homeActivity.checkFragmentOnActivity()
        val data = this.arguments
        val id = data?.getInt(Utils.ID_PRODUCT)
        setUpTabLayout()
        setQuantityCart()
        id?.let { setDataForProductDetail(it) }
        binding.btnAddCart.setOnClickListener {
            with(cartViewModel) {
                insertCar(cart)
                if (checkCartItemExist(cart)) {
                    Toast.makeText(requireContext(), "Item is exist", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnAdd.setOnClickListener {
            cartViewModel.addItem()
        }

        binding.btnSubtract.setOnClickListener {
            cartViewModel.subtractItem()
        }

        binding.back.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        binding.btnCart.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_current, YourCartFragment())?.addToBackStack(null)?.commit()
        }
    }

    private fun setDataForProductDetail(id: Int) {
        RetrofitInstance.apiInterface.getProductDetail(id)
            .enqueue(object : Callback<ProductDetail?> {
                override fun onResponse(
                    call: Call<ProductDetail?>,
                    response: Response<ProductDetail?>
                ) {
                    val product = response.body()
                    productViewModel.apply {
                        product?.let { setProduct(it) }
                        productDetail.observe(viewLifecycleOwner) { product ->
                            if (product != null) {
                                with(binding) {
                                    with(product) {
                                        tvNameProduct.text = name
                                        Glide.with(requireContext()).load(image).fitCenter()
                                            .into(imgProduct)
                                        tvPromotion.text =
                                            Utils.formatCurrency.format(price).toString()
                                        tvPriceProduct.text =
                                            Utils.formatCurrency.format(sale_price).toString()
                                        with(layoutBrandRateProductDetail) {
                                            tvNameBrand.text = seller
                                            tvRate.text = star.toString()
                                        }
                                        cartViewModel.quantity.observe(viewLifecycleOwner) { quantity ->
                                            cart = Cart(
                                                id = id,
                                                quantityItem = quantity,
                                                sumPrice = sale_price * quantity,
                                                imageCart = image,
                                                nameCart = name,
                                                checkCart = false
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                }

                override fun onFailure(call: Call<ProductDetail?>, t: Throwable) {
                    Log.e("Error Api", "${t.message}")
                }
            })

    }

    private fun setQuantityCart() {
        with(cartViewModel) {
            listCart.observe(viewLifecycleOwner) { list ->
                quantity.observe(viewLifecycleOwner) { quantity ->
                    with(binding) {
                        tvStatusQuantity.text = list.size.toString()
                        tvQuantity.text = quantity.toString()
                    }
                }
            }
        }

    }

    private fun setUpTabLayout() {
        val adapter = DetailViewPagerAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Details"
                1 -> tab.text = "Review"
            }
        }.attach()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> binding.btnAdd.setImageResource(R.drawable.add)
                    1 -> binding.btnAdd.setImageResource(R.drawable.add_color)
                    else -> binding.btnAdd.setImageResource(R.drawable.add)
                }
                super.onPageSelected(position)
            }
        })

    }

}