package com.example.bloomifyshop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bloomifyshop.R
import com.example.bloomifyshop.adapters.PaymentMethodAdapter
import com.example.bloomifyshop.models.PaymentMethod
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox

class PaymentMethodsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment_methods, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBackButton(view)
        setupRecyclerView(view)
        setupAddButton(view)
    }

    private fun setupBackButton(view: View) {
        view.findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.paymentMethodsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = PaymentMethodAdapter(
            getDummyPaymentMethods(),
            onEditClick = { paymentMethod ->
                showEditDialog(paymentMethod)
            }
        )
    }

    private fun setupAddButton(view: View) {
        view.findViewById<FloatingActionButton>(R.id.addPaymentButton).setOnClickListener {
            // TODO: Implement add payment method functionality
        }
    }

    private fun getDummyPaymentMethods(): List<PaymentMethod> {
        return listOf(
            PaymentMethod(
                id = "1",
                cardNumber = "**** **** **** 1234",
                cardHolder = "John Doe",
                expiryDate = "12/25",
                type = "VISA",
                isDefault = true
            ),
            PaymentMethod(
                id = "2",
                cardNumber = "**** **** **** 5678",
                cardHolder = "John Doe",
                expiryDate = "09/24",
                type = "Mastercard",
                isDefault = false
            )
        )
    }

    private fun showEditDialog(paymentMethod: PaymentMethod) {
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setView(R.layout.dialog_edit_payment)
            .show()

        dialog.apply {
            findViewById<TextInputEditText>(R.id.cardNumberInput)?.setText(paymentMethod.cardNumber)
            findViewById<TextInputEditText>(R.id.cardHolderInput)?.setText(paymentMethod.cardHolder)
            findViewById<TextInputEditText>(R.id.expiryDateInput)?.setText(paymentMethod.expiryDate)
            findViewById<MaterialCheckBox>(R.id.defaultCheckbox)?.isChecked = paymentMethod.isDefault

            findViewById<MaterialButton>(R.id.cancelButton)?.setOnClickListener {
                dismiss()
            }

            findViewById<MaterialButton>(R.id.saveButton)?.setOnClickListener {
                // TODO: Validate inputs
                val updatedPaymentMethod = PaymentMethod(
                    id = paymentMethod.id,
                    cardNumber = findViewById<TextInputEditText>(R.id.cardNumberInput)?.text.toString(),
                    cardHolder = findViewById<TextInputEditText>(R.id.cardHolderInput)?.text.toString(),
                    expiryDate = findViewById<TextInputEditText>(R.id.expiryDateInput)?.text.toString(),
                    type = paymentMethod.type,
                    isDefault = findViewById<MaterialCheckBox>(R.id.defaultCheckbox)?.isChecked ?: false
                )
                // TODO: Update payment method in data source
                dismiss()
                // Refresh the list
                setupRecyclerView(requireView())
            }
        }
    }
} 