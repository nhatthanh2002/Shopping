package com.nhatthanh.shopping.product.adapter

import android.app.Notification
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhatthanh.shopping.databinding.ItemNotificationBinding
import com.nhatthanh.shopping.product.model.Notifications

class NotificationAdapter(private val context: Context, private val list: List<Notifications>) :
    RecyclerView.Adapter<NotificationAdapter.MyViewMolder>() {
    inner class MyViewMolder(binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val img = binding.banner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewMolder {
        return MyViewMolder(
            ItemNotificationBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewMolder, position: Int) {
        with(holder) {
            with(list[position]) {
                img.setImageResource(imgNotification)
            }
        }
    }

    override fun getItemCount(): Int = list.size
}