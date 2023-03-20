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
        val listAll = ArrayList<Cart>()
        with(binding) {
            with(cartViewModel) {
                checkAll.setOnClickListener {
                    if (checkAll.isChecked) {
                        setCheckCartAll(true)
                        listCart.observe(viewLifecycleOwner) {
                            //cartAdapter.submitList(it)
                            listAll.addAll(it)
                            Log.e("List True", "${listAll.size}")
                            setListCartSelected(listAll)
                        }
                        binding.tvBuyItem.text = getQuantityItemSelected()
                        binding.totalPrice.text = getTotalPriceCart()

                    } else {
                        setCheckCartAll(false)
                        listCart.observe(viewLifecycleOwner) {
                            //cartAdapter.submitList(it)
                            listAll.clear()
                            Log.e("List false", "${listAll.size}")
                            setListCartSelected(listAll)
                        }
                        binding.tvBuyItem.text = getQuantityItemSelected()
                        binding.totalPrice.text = getTotalPriceCart()

                    }
                }

                listCart.observe(viewLifecycleOwner) { listCartItem ->
                    listCartSelected.observe(viewLifecycleOwner) { listCartSelectedItem ->
                        checkAll.isChecked = listCartSelectedItem.size == listCartItem.size
                    }
                }
            }
        }
    }

    override fun listenUpdateCartItem(id: Int, check: Boolean) {
        cartViewModel.setCheckItemCart(id, check)
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
            binding.tvBuyItem.text = getQuantityItemSelected()
            binding.totalPrice.text = getTotalPriceCart()
        }

    }
}