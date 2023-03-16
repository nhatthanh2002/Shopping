package com.nhatthanh.shopping.product.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nhatthanh.shopping.R
import com.nhatthanh.shopping.Utils
import com.nhatthanh.shopping.databinding.FragmentYourCartBinding
import com.nhatthanh.shopping.localData.application.MainApplication
import com.nhatthanh.shopping.product.activity.HomeActivity
import com.nhatthanh.shopping.product.adapter.CarAdapter
import com.nhatthanh.shopping.product.adapter.DiffUtilCarAdapter
import com.nhatthanh.shopping.product.listenerevent.CartListener
import com.nhatthanh.shopping.product.model.Cart
import com.nhatthanh.shopping.product.viewmodel.CartMolderFactory
import com.nhatthanh.shopping.product.viewmodel.CartViewModel

class YourCartFragment : Fragment(), CartListener {
    private lateinit var binding: FragmentYourCartBinding
    private lateinit var homeActivity: HomeActivity
    private lateinit var carAdapter: CarAdapter

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
        homeActivity.checkFragmentOnActivity()
        binding.tvBuyItem.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_current, CheckOutFragment())?.addToBackStack(null)
                ?.commit()
        }
        binding.btnBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }


        cartViewModel.listCart.observe(viewLifecycleOwner) { listCart ->
            binding.rvCart.setHasFixedSize(true)
            binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
            carAdapter = CarAdapter(requireContext(), listCart, this)
            binding.rvCart.adapter = carAdapter
        }
    }

    private fun checkCartSelected(id: Int, selected: Boolean) {
        cartViewModel.updateCartSelected(id, selected)
    }

    private fun choiceAllItem() {
        with(binding) {
            checkAll.setOnClickListener {
                if (checkAll.isChecked) {
                    cartViewModel.listCartSelected.observe(viewLifecycleOwner) { listItem ->
                        listItem.map {

                        }
                    }
                }
            }
        }
    }

    override fun setCheckItem(cart: Cart, id: Int) {
    }

    override fun addCart(id: Int, add: Int) {
        var quantity = add
        quantity += 1
        cartViewModel.updateQuantity(id, quantity)
    }

    override fun subtractCart(id: Int, subtract: Int) {
        var quantity = subtract
        quantity -= 1
        cartViewModel.updateQuantity(id, quantity)
    }


    override fun deleteCart(id: Int) {
        cartViewModel.deleteCart(id)
    }

    @SuppressLint("SetTextI18n")
    override fun cartSelected(list: ArrayList<Cart>) {
        var total = 0.0
        cartViewModel.apply {
            getListCartSelected(list)
            listCartSelected.observe(viewLifecycleOwner) { list ->
                setItemSelected(list)
                for (item in list) {
                    total += (item.sumPrice * item.quantityItem)
                }

                setTotalPriceCart(total)
                totalPriceItemCart.observe(viewLifecycleOwner) {
                    binding.totalPrice.text = Utils.formatCurrency.format(it).toString()
                }

            }
            quantityCarSelected.observe(viewLifecycleOwner) {
                binding.tvBuyItem.text = "Buy ($it item)"
            }
        }

    }
}