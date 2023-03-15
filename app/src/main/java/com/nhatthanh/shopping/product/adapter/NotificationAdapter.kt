package com.nhatthanh.shopping.product.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nhatthanh.shopping.databinding.ItemNotificationBinding
import com.nhatthanh.shopping.product.model.Notifications

@Suppress("DEPRECATION")
class NotificationAdapter(private val context: Context, private val list: List<Notifications>) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    inner class NotificationViewHolder(val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Notifications) {
            with(binding) {
                with(item) {
                    Glide.with(context)
                        .load(image)
                        .override(250, 120)
                        .centerCrop()
                        .into(banner)
                    banner.setOnClickListener {
                        Log.e("Position Banner","$adapterPosition")
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(
            ItemNotificationBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}