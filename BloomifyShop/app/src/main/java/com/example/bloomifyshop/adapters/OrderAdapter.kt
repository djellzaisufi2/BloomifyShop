package com.example.bloomifyshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bloomifyshop.R
import com.example.bloomifyshop.models.Order

class OrderAdapter(private val orders: List<Order>) : 
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val orderId: TextView = view.findViewById(R.id.orderId)
        val orderDate: TextView = view.findViewById(R.id.orderDate)
        val itemsRecyclerView: RecyclerView = view.findViewById(R.id.orderItemsRecycler)
        val orderTotal: TextView = view.findViewById(R.id.orderTotal)
        val orderStatus: TextView = view.findViewById(R.id.orderStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        
        holder.orderId.text = "Order #${order.id}"
        holder.orderDate.text = order.date
        holder.orderTotal.text = String.format("$%.2f", order.total)
        holder.orderStatus.text = order.status
        
        // Setup nested RecyclerView for order items
        holder.itemsRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.itemsRecyclerView.adapter = OrderItemAdapter(order.items)
        
        holder.orderStatus.setTextColor(
            when (order.status) {
                "Delivered" -> holder.itemView.context.getColor(R.color.primary)
                "Processing" -> holder.itemView.context.getColor(R.color.accent)
                else -> holder.itemView.context.getColor(R.color.text_secondary)
            }
        )
    }

    override fun getItemCount() = orders.size
} 