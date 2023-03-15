package com.nhatthanh.shopping.product.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nhatthanh.shopping.callapi.RetrofitInstance
import com.nhatthanh.shopping.databinding.FragmentReviewBinding
import com.nhatthanh.shopping.product.adapter.ReviewAdapter
import com.nhatthanh.shopping.product.model.Reviews
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewFragment : Fragment() {
    private lateinit var binding: FragmentReviewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListReviews()
    }

    private fun setUpListReviews() {
        RetrofitInstance.apiInterface.getListReview().enqueue(object : Callback<List<Reviews>?> {
            override fun onResponse(
                call: Call<List<Reviews>?>,
                response: Response<List<Reviews>?>
            ) {
                val list=response.body()
                binding.rvReviews.apply {
                    setHasFixedSize(true)
                    layoutManager=LinearLayoutManager(requireContext())
                    adapter= list?.let { ReviewAdapter(it) }
                }
            }

            override fun onFailure(call: Call<List<Reviews>?>, t: Throwable) {
                Log.e("Error api","${t.message}")
            }
        })
    }

}