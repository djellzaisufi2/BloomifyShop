package com.example.bloomifyshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bloomifyshop.R
import com.example.bloomifyshop.models.Address

class AddressAdapter(
    private val addresses: List<Address>,
    private val onEditClick: (Address) -> Unit
) : RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {

    class AddressViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val street: TextView = view.findViewById(R.id.street)
        val cityStateZip: TextView = view.findViewById(R.id.cityStateZip)
        val phone: TextView = view.findViewById(R.id.phone)
        val defaultTag: View = view.findViewById(R.id.defaultTag)
        val deleteButton: ImageButton = view.findViewById(R.id.deleteButton)
        val editButton: ImageButton = view.findViewById(R.id.editButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_address, parent, false)
        return AddressViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val address = addresses[position]
        
        holder.name.text = address.name
        holder.street.text = address.street
        holder.cityStateZip.text = "${address.city} ${address.postalCode}"
        holder.phone.text = address.phone
        holder.defaultTag.visibility = if (address.isDefault) View.VISIBLE else View.GONE
        
        holder.deleteButton.setOnClickListener {
            // TODO: Implement delete functionality
        }
        
        holder.editButton.setOnClickListener {
            onEditClick(address)
        }
    }

    override fun getItemCount() = addresses.size
} 