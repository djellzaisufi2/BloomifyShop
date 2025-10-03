package com.example.bloomifyshop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.bloomifyshop.MainActivity
import com.example.bloomifyshop.R
import com.google.android.material.button.MaterialButton

class OrderConfirmationFragment : Fragment() {
    private var orderId: String = ""

    companion object {
        fun newInstance(orderId: String): OrderConfirmationFragment {
            return OrderConfirmationFragment().apply {
                this.orderId = orderId
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.orderNumberText).text = "Order #$orderId"

        view.findViewById<MaterialButton>(R.id.viewOrderButton).setOnClickListener {
            // Navigate to order details
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, OrderHistoryFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<MaterialButton>(R.id.continueShoppingButton).setOnClickListener {
            // Navigate back to home
            (activity as? MainActivity)?.loadFragment(HomeFragment())
        }
    }
} 