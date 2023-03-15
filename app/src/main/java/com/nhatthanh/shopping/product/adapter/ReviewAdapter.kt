package com.nhatthanh.shopping.product.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhatthanh.shopping.databinding.ItemColorBinding
import com.nhatthanh.shopping.databinding.ItemReviewBinding
import com.nhatthanh.shopping.product.model.Reviews
import java.text.SimpleDateFormat
import java.util.Date

class ReviewAdapter(private val list: List<Reviews>) :
    RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    @SuppressLint("SimpleDateFormat")
    val format = SimpleDateFormat("dd MMMM yyyy")

    inner class ReviewViewHolder(val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Reviews) {
            with(binding) {
                with(item) {
                    tvNameUser.text = user_name
                    tvContent.text = content
                    val date = Date(time.toLong())
                    tvTime.text = format.format(date)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
            ItemReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}