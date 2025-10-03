package com.example.bloomifyshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bloomifyshop.R
import com.example.bloomifyshop.models.CartItem

class CartAdapter(
    private val cartItems: List<CartItem>,
    private val onQuantityChanged: (CartItem, Int) -> Unit,
    private val onDeleteClick: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.item_image)
        val nameText: TextView = view.findViewById(R.id.item_name)
        val priceText: TextView = view.findViewById(R.id.item_price)
        val quantityText: TextView = view.findViewById(R.id.quantityText)
        val decreaseButton: ImageButton = view.findViewById(R.id.decreaseBtn)
        val increaseButton: ImageButton = view.findViewById(R.id.increaseBtn)
        val deleteButton: ImageButton = view.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]
        
        holder.imageView.setImageResource(item.imageResource)
        holder.nameText.text = item.name
        holder.priceText.text = String.format("$%.2f", item.price)
        holder.quantityText.text = item.quantity.toString()

        holder.decreaseButton.setOnClickListener {
            if (item.quantity > 1) {
                onQuantityChanged(item, item.quantity - 1)
            }
        }

        holder.increaseButton.setOnClickListener {
            onQuantityChanged(item, item.quantity + 1)
        }

        holder.deleteButton.setOnClickListener {
            onDeleteClick(item)
        }
    }

    override fun getItemCount() = cartItems.size
} 