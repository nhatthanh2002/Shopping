package com.nhatthanh.shopping.product.fragment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.nhatthanh.shopping.R
import com.nhatthanh.shopping.databinding.FragmentCheckOutBinding
import com.nhatthanh.shopping.localData.application.MainApplication
import com.nhatthanh.shopping.product.adapter.BuyItemAdapter
import com.nhatthanh.shopping.product.listenerevent.GetIDItemCarSelected
import com.nhatthanh.shopping.product.viewmodel.CartMolderFactory
import com.nhatthanh.shopping.product.viewmodel.CartViewModel


class CheckOutFragment : Fragment(), GetIDItemCarSelected {
    private lateinit var binding: FragmentCheckOutBinding
    private val cartViewModel: CartViewModel by activityViewModels {
        CartMolderFactory((requireActivity().application as MainApplication).cartRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckOutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener { activity?.supportFragmentManager?.popBackStack() }
        binding.tvContinue.setOnClickListener {

            cartViewModel.apply {
                listIdCart.observe(viewLifecycleOwner) { listID ->
                    for (id in listID) {
                        deleteCart(id)
                    }
                }
            }
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_current, HomeFragment())?.commit()
            openDialogSuccess()
        }

        with(binding) {
            with(cartViewModel) {
                tvTotalPay.text = getTotalPriceCart()
                listCartSelected.observe(viewLifecycleOwner) { list ->
                    rvBuyItem.apply {
                        setHasFixedSize(true)
                        adapter = BuyItemAdapter(requireContext(), list, this@CheckOutFragment)
                    }
                }
            }
        }
    }

    private fun openDialogSuccess() {
        val dialog = Dialog(requireContext())
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_order_success)
        val ok: TextView = dialog.findViewById(R.id.btn_ok)
        ok.setOnClickListener {
            Log.e("Click Dilog", "Click Dilog")
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun getListIDSelected(listID: ArrayList<Int>) {
        cartViewModel.setListIDCart(listID)
    }

}