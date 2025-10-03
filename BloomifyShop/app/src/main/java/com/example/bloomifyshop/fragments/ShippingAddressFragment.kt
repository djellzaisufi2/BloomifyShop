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
import com.example.bloomifyshop.adapters.AddressAdapter
import com.example.bloomifyshop.models.Address
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.example.bloomifyshop.utils.AddressManager
import android.widget.Toast

class ShippingAddressFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shipping_address, container, false)
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
        recyclerView = view.findViewById(R.id.addressRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        updateAddressList()
    }

    private fun updateAddressList() {
        recyclerView.adapter = AddressAdapter(
            AddressManager.getAddresses(),
            onEditClick = { address ->
                showEditDialog(address)
            }
        )
    }

    private fun setupAddButton(view: View) {
        view.findViewById<FloatingActionButton>(R.id.addAddressButton).setOnClickListener {
            showNewAddressDialog()
        }
    }

    private fun showNewAddressDialog() {
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setView(R.layout.dialog_edit_address)
            .show()

        dialog.apply {
            findViewById<MaterialButton>(R.id.cancelButton)?.setOnClickListener {
                dismiss()
            }

            findViewById<MaterialButton>(R.id.saveButton)?.setOnClickListener {
                val name = findViewById<TextInputEditText>(R.id.nameInput)?.text.toString()
                val street = findViewById<TextInputEditText>(R.id.streetInput)?.text.toString()
                val city = findViewById<TextInputEditText>(R.id.cityInput)?.text.toString()
                val postalCode = findViewById<TextInputEditText>(R.id.postalCodeInput)?.text.toString()
                val phone = findViewById<TextInputEditText>(R.id.phoneInput)?.text.toString()
                val isDefault = findViewById<MaterialCheckBox>(R.id.defaultCheckbox)?.isChecked ?: false

                if (validateAddress(name, street, city, postalCode, phone)) {
                    val newAddress = Address(
                        id = System.currentTimeMillis().toString(),
                        name = name,
                        street = street,
                        city = city,
                        postalCode = postalCode,
                        phone = phone,
                        isDefault = isDefault
                    )
                    AddressManager.addAddress(newAddress)
                    updateAddressList()
                    dismiss()
                    Toast.makeText(context, "Address added successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showEditDialog(address: Address) {
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setView(R.layout.dialog_edit_address)
            .show()

        dialog.apply {
            // Pre-fill the fields
            findViewById<TextInputEditText>(R.id.nameInput)?.setText(address.name)
            findViewById<TextInputEditText>(R.id.streetInput)?.setText(address.street)
            findViewById<TextInputEditText>(R.id.cityInput)?.setText(address.city)
            findViewById<TextInputEditText>(R.id.postalCodeInput)?.setText(address.postalCode)
            findViewById<TextInputEditText>(R.id.phoneInput)?.setText(address.phone)
            findViewById<MaterialCheckBox>(R.id.defaultCheckbox)?.isChecked = address.isDefault

            findViewById<MaterialButton>(R.id.cancelButton)?.setOnClickListener {
                dismiss()
            }

            findViewById<MaterialButton>(R.id.saveButton)?.setOnClickListener {
                val name = findViewById<TextInputEditText>(R.id.nameInput)?.text.toString()
                val street = findViewById<TextInputEditText>(R.id.streetInput)?.text.toString()
                val city = findViewById<TextInputEditText>(R.id.cityInput)?.text.toString()
                val postalCode = findViewById<TextInputEditText>(R.id.postalCodeInput)?.text.toString()
                val phone = findViewById<TextInputEditText>(R.id.phoneInput)?.text.toString()
                val isDefault = findViewById<MaterialCheckBox>(R.id.defaultCheckbox)?.isChecked ?: false

                if (validateAddress(name, street, city, postalCode, phone)) {
                    val updatedAddress = Address(
                        id = address.id,
                        name = name,
                        street = street,
                        city = city,
                        postalCode = postalCode,
                        phone = phone,
                        isDefault = isDefault
                    )
                    AddressManager.updateAddress(address, updatedAddress)
                    updateAddressList()
                    dismiss()
                    Toast.makeText(context, "Address updated successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validateAddress(
        name: String,
        street: String,
        city: String,
        postalCode: String,
        phone: String
    ): Boolean {
        return name.isNotBlank() &&
               street.isNotBlank() &&
               city.isNotBlank() &&
               postalCode.length >= 5 &&
               phone.length >= 10
    }
} 