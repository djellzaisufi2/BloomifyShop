package com.example.bloomifyshop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.bloomifyshop.R
import com.google.android.material.materialswitch.MaterialSwitch

class NotificationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupBackButton(view)
        setupSwitches(view)
    }

    private fun setupBackButton(view: View) {
        view.findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupSwitches(view: View) {
        // Order Updates
        view.findViewById<MaterialSwitch>(R.id.orderUpdatesSwitch)?.apply {
            isChecked = true // Default value
            setOnCheckedChangeListener { _, isChecked ->
                // TODO: Save preference
            }
        }

        // Promotions
        view.findViewById<MaterialSwitch>(R.id.promotionsSwitch)?.apply {
            isChecked = true
            setOnCheckedChangeListener { _, isChecked ->
                // TODO: Save preference
            }
        }

        // New Arrivals
        view.findViewById<MaterialSwitch>(R.id.newArrivalsSwitch)?.apply {
            isChecked = true
            setOnCheckedChangeListener { _, isChecked ->
                // TODO: Save preference
            }
        }

        // Reminders
        view.findViewById<MaterialSwitch>(R.id.remindersSwitch)?.apply {
            isChecked = true
            setOnCheckedChangeListener { _, isChecked ->
                // TODO: Save preference
            }
        }
    }
} 