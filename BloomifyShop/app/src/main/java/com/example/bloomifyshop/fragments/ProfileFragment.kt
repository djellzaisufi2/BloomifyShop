package com.example.bloomifyshop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bloomifyshop.R
import com.example.bloomifyshop.utils.UserManager
import com.google.android.material.button.MaterialButton

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Display user information
        val userName = view.findViewById<TextView>(R.id.userName)
        val userEmail = view.findViewById<TextView>(R.id.userEmail)
        
        userName.text = UserManager.getUserName().ifEmpty { "Guest User" }
        userEmail.text = UserManager.getUserEmail().ifEmpty { "No email provided" }
        
        setupClickListeners(view)
    }

    private fun setupClickListeners(view: View) {
        // Order History
        view.findViewById<TextView>(R.id.orderHistoryButton).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, OrderHistoryFragment())
                .addToBackStack(null)
                .commit()
        }

        // Shipping Address
        view.findViewById<TextView>(R.id.addressButton).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ShippingAddressFragment())
                .addToBackStack(null)
                .commit()
        }

        // Payment Methods
        view.findViewById<TextView>(R.id.paymentButton).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PaymentMethodsFragment())
                .addToBackStack(null)
                .commit()
        }

        // Notifications
        view.findViewById<TextView>(R.id.notificationsButton).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NotificationsFragment())
                .addToBackStack(null)
                .commit()
        }

        // Privacy Policy
        view.findViewById<TextView>(R.id.privacyButton).setOnClickListener {
            showToast("Privacy Policy")
        }

        // Logout
        view.findViewById<MaterialButton>(R.id.logoutButton).setOnClickListener {
            showToast("Logging out...")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
} 