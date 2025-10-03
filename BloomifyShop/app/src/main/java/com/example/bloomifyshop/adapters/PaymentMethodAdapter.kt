package com.example.bloomifyshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bloomifyshop.R
import com.example.bloomifyshop.models.PaymentMethod

class PaymentMethodAdapter(
    private val paymentMethods: List<PaymentMethod>,
    private val onEditClick: (PaymentMethod) -> Unit
) : RecyclerView.Adapter<PaymentMethodAdapter.PaymentMethodViewHolder>() {

    class PaymentMethodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardNumber: TextView = view.findViewById(R.id.cardNumber)
        val cardHolder: TextView = view.findViewById(R.id.cardHolder)
        val expiryDate: TextView = view.findViewById(R.id.expiryDate)
        val cardType: TextView = view.findViewById(R.id.cardType)
        val defaultTag: View = view.findViewById(R.id.defaultTag)
        val deleteButton: ImageButton = view.findViewById(R.id.deleteButton)
        val editButton: ImageButton = view.findViewById(R.id.editButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_payment_method, parent, false)
        return PaymentMethodViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {
        val paymentMethod = paymentMethods[position]
        
        holder.cardNumber.text = paymentMethod.cardNumber
        holder.cardHolder.text = paymentMethod.cardHolder
        holder.expiryDate.text = paymentMethod.expiryDate
        holder.cardType.text = paymentMethod.type
        holder.defaultTag.visibility = if (paymentMethod.isDefault) View.VISIBLE else View.GONE
        
        holder.deleteButton.setOnClickListener {
            // TODO: Implement delete functionality
        }
        
        holder.editButton.setOnClickListener {
            onEditClick(paymentMethod)
        }
    }

    override fun getItemCount() = paymentMethods.size
} 