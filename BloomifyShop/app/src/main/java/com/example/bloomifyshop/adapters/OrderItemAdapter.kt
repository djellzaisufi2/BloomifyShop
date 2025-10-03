package com.example.bloomifyshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bloomifyshop.R
import com.example.bloomifyshop.models.OrderItem

class OrderItemAdapter(private val items: List<OrderItem>) :
    RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder>() {

    class OrderItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val flowerImage: ImageView = view.findViewById(R.id.flowerImage)
        val flowerName: TextView = view.findViewById(R.id.flowerName)
        val quantity: TextView = view.findViewById(R.id.quantity)
        val price: TextView = view.findViewById(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order_flower, parent, false)
        return OrderItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        val item = items[position]
        
        holder.flowerImage.setImageResource(item.flower.imageResource)
        holder.flowerName.text = item.flower.name
        holder.quantity.text = "x${item.quantity}"
        holder.price.text = String.format("$%.2f", item.flower.price * item.quantity)
    }

    override fun getItemCount() = items.size
} 