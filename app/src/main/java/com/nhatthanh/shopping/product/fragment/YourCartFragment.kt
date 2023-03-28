package com.nhatthanh.shopping.product.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nhatthanh.shopping.R
import com.nhatthanh.shopping.databinding.FragmentYourCartBinding
import com.nhatthanh.shopping.localData.application.MainApplication
import com.nhatthanh.shopping.product.activity.HomeActivity
import com.nhatthanh.shopping.product.adapter.DiffUtilCarAdapter
import com.nhatthanh.shopping.product.listenerevent.CartListener
import com.nhatthanh.shopping.product.model.Cart
import com.nhatthanh.shopping.product.viewmodel.CartMolderFactory
import com.nhatthanh.shopping.product.viewmodel.CartViewModel
import kotlinx.coroutines.launch

class YourCartFragment : Fragment(), CartListener {
    private lateinit var binding: FragmentYourCartBinding
    private lateinit var homeActivity: HomeActivity
    private lateinit var cartAdapter: DiffUtilCarAdapter
    private val cartViewModel: CartViewModel by activityViewModels {
        CartMolderFactory((requireActivity().application as MainApplication).cartRepository)
    }

    private lateinit var listListenerItemCart: ArrayList<Cart>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentYourCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeActivity = activity as HomeActivity
        listListenerItemCart = ArrayList()
        choiceAllItem()
        buyItem()
        homeActivity.checkFragmentOnActivity()

        binding.btnBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        cartAdapter = DiffUtilCarAdapter(
            requireContext(),
            this
        )

        lifecycleScope.launch {
            cartViewModel.listCart.observe(viewLifecycleOwner) { listCart ->
                cartAdapter.submitList(listCart)
            }
        }

        binding.rvCart.setHasFixedSize(true)
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCart.adapter = cartAdapter

    }

    private fun buyItem() {
        binding.tvBuyItem.setBackgroundResource(R.drawable.custom_button_enable)
        cartViewModel.listCartSelected.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.tvBuyItem.setBackgroundResource(R.drawable.custom_button)
                binding.tvBuyItem.isEnabled = true
                binding.tvBuyItem.setOnClickListener {
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragment_current, CheckOutFragment())?.addToBackStack(null)
                        ?.commit()
                }
            } else {
                binding.tvBuyItem.setBackgroundResource(R.drawable.custom_button_enable)
                binding.tvBuyItem.isEnabled = false
            }
        }

    }


    @SuppressLint("SetTextI18n")
    private fun choiceAllItem() {
        with(binding) {
            with(cartViewModel) {
                checkAll.setOnClickListener {
                    if (checkAll.isChecked) {
                        setCheckCartAll(true)
                        clearListCartSelected()
                        listCart.observe(viewLifecycleOwner) {
                            if (listListenerItemCart.size == it.size)
                                return@observe
                            listListenerItemCart.addAll(it)
                        }
                        setListCartSelected(listListenerItemCart)
                        listCartSelected.observe(viewLifecycleOwner) {
                            Log.e("Size list True", "${it.size}")
                        }
                    } else {
                        setCheckCartAll(false)
                        clearListCartSelected()
                        listListenerItemCart.clear()
                        listCartSelected.observe(viewLifecycleOwner) {
                            Log.e("Size list False", "${it.size}")
                        }
                        setListCartSelected(listListenerItemCart)
                    }
                    binding.tvBuyItem.text = getQuantityItemSelected()
                    binding.totalPrice.text = getTotalPriceCart()
                    buyItem()
                }

                listCart.observe(viewLifecycleOwner) {
                    listCartSelected.observe(viewLifecycleOwner){listSelected->
                        checkAll.isChecked = listSelected.size==it.size
                    }
                }

            }
        }
    }

    override fun listenUpdateCartItem(id: Int, check: Boolean) {
        cartViewModel.setCheckItemCart(id, check)
    }

    override fun listenUpdateQuantityItem(id: Int, quantity: Int) {
        cartViewModel.setQuantityItemCart(id, quantity)
    }


    override fun addCart(id: Int, add: Int) {
        var quantity = add
        quantity += 1
        cartViewModel.apply {
            updateQuantity(id, quantity)
        }

    }

    override fun subtractCart(id: Int, subtract: Int) {
        var quantity = subtract
        quantity -= 1
        cartViewModel.apply {
            updateQuantity(id, quantity)
        }
    }


    override fun deleteCart(id: Int) {
        cartViewModel.deleteCart(id)
    }

    @SuppressLint("SetTextI18n")
    override fun cartSelected(list: ArrayList<Cart>) {
        cartViewModel.apply {
            setListCartSelected(list)
            Log.e("List","${list.size}")
            binding.tvBuyItem.text = getQuantityItemSelected()
            binding.totalPrice.text = getTotalPriceCart()
        }
    }
}