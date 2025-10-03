package com.example.bloomifyshop.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AlertDialog.Builder
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bloomifyshop.MainActivity
import com.example.bloomifyshop.R
import com.example.bloomifyshop.adapters.CartAdapter
import com.example.bloomifyshop.models.CartItem
import com.example.bloomifyshop.models.Address
import com.example.bloomifyshop.utils.CartManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.example.bloomifyshop.utils.CardFormatter
import android.widget.ImageView
import com.example.bloomifyshop.utils.AddressManager

class CartFragment : Fragment() {

    private lateinit var emptyStateLayout: LinearLayout
    private lateinit var cartContent: LinearLayout
    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var subtotalText: TextView
    private lateinit var taxText: TextView
    private lateinit var deliveryText: TextView
    private lateinit var totalText: TextView
    private lateinit var checkoutButton: MaterialButton

    private var cartItems = mutableListOf<CartItem>()
    private val TAX_RATE = 0.10 // 10% tax
    private val DELIVERY_FEE = 5.99
    private var savedAddresses = mutableListOf<Address>()
    private var selectedAddress: Address? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupViews(view)
        setupBackButton(view)
        setupStartShoppingButton(view)
        loadCartItems()
    }

    private fun setupViews(view: View) {
        emptyStateLayout = view.findViewById(R.id.emptyStateLayout)
        cartContent = view.findViewById(R.id.cartContent)
        cartRecyclerView = view.findViewById(R.id.cartRecyclerView)
        subtotalText = view.findViewById(R.id.subtotalTxt)
        taxText = view.findViewById(R.id.taxTxt)
        deliveryText = view.findViewById(R.id.deliveryTxt)
        totalText = view.findViewById(R.id.totalTxt)
        checkoutButton = view.findViewById(R.id.checkoutBtn)

        cartRecyclerView.layoutManager = LinearLayoutManager(context)
        
        checkoutButton.setOnClickListener {
            if (cartItems.isNotEmpty()) {
                proceedToCheckout()
            }
        }
    }

    private fun setupBackButton(view: View) {
        // Remove the back button setup since it's causing the app to close
        // view.findViewById<ImageButton>(R.id.backBtn)?.visibility = View.GONE
    }

    private fun setupStartShoppingButton(view: View) {
        view.findViewById<MaterialButton>(R.id.startShoppingBtn).setOnClickListener {
            // Navigate to home fragment instead of popping back stack
            (activity as? MainActivity)?.loadFragment(HomeFragment())
        }
    }

    private fun loadCartItems() {
        cartItems = CartManager.getCartItems().toMutableList()
        updateCartDisplay()
    }

    private fun updateCartDisplay() {
        if (cartItems.isEmpty()) {
            emptyStateLayout.visibility = View.VISIBLE
            cartContent.visibility = View.GONE
        } else {
            emptyStateLayout.visibility = View.GONE
            cartContent.visibility = View.VISIBLE

            // Only create new adapter if needed
            if (cartRecyclerView.adapter == null) {
                cartRecyclerView.adapter = CartAdapter(
                    cartItems,
                    onQuantityChanged = { item, newQuantity ->
                        updateItemQuantity(item, newQuantity)
                    },
                    onDeleteClick = { item ->
                        removeItemWithAnimation(item)
                    }
                )
            }
            updateTotals()
        }
    }

    private fun updateItemQuantity(item: CartItem, newQuantity: Int) {
        item.quantity = newQuantity
        updateTotals()
    }

    private fun removeItemWithAnimation(item: CartItem) {
        val position = cartItems.indexOf(item)
        if (position == -1) return

        CartManager.removeItem(item)
        cartItems.remove(item)
        cartRecyclerView.adapter?.notifyItemRemoved(position)
        
        val slideOut = AnimationUtils.loadAnimation(context, R.anim.slide_out_left)
        cartRecyclerView.startAnimation(slideOut)
        
        updateCartDisplay()
    }

    private fun updateTotals() {
        val subtotal = cartItems.sumOf { it.price * it.quantity }
        val tax = subtotal * TAX_RATE
        val total = subtotal + tax + DELIVERY_FEE

        // Animate the price changes
        animateTextChange(subtotalText, String.format("$%.2f", subtotal))
        animateTextChange(taxText, String.format("$%.2f", tax))
        animateTextChange(deliveryText, String.format("$%.2f", DELIVERY_FEE))
        animateTextChange(totalText, String.format("$%.2f", total))
    }

    private fun animateTextChange(textView: TextView, newValue: String) {
        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.duration = 200
        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                textView.text = newValue
                textView.startAnimation(AlphaAnimation(0f, 1f).apply { duration = 200 })
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })
        textView.startAnimation(fadeOut)
    }

    private fun proceedToCheckout() {
        // First select shipping address
        showAddressSelection()
    }

    private fun showAddressSelection() {
        val addresses = AddressManager.getAddresses()
        if (addresses.isEmpty()) {
            showNewAddressDialog()
        } else {
            val addressItems = addresses.map { 
                "${it.name}\n${it.street}, ${it.city}"
            }.toMutableList()
            addressItems.add("+ Add New Address")

            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Select Shipping Address")
                .setItems(addressItems.toTypedArray()) { _, which ->
                    if (which == addresses.size) {
                        showNewAddressDialog()
                    } else {
                        selectedAddress = addresses[which]
                        showPaymentSelection()
                    }
                }
                .setPositiveButton("Edit Addresses") { _, _ ->
                    showAddressManagement()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    private fun showAddressManagement() {
        val addresses = AddressManager.getAddresses()
        val addressItems = addresses.map { 
            "${it.name}\n${it.street}, ${it.city}"
        }.toTypedArray()

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Manage Addresses")
            .setItems(addressItems) { _, which ->
                showEditAddressDialog(addresses[which])
            }
            .setPositiveButton("Add New") { _, _ ->
                showNewAddressDialog()
            }
            .setNegativeButton("Done", null)
            .show()
    }

    private fun showEditAddressDialog(address: Address) {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_shipping_address, null)

        // Pre-fill the fields
        dialogView.findViewById<TextInputEditText>(R.id.nameInput).setText(address.name)
        dialogView.findViewById<TextInputEditText>(R.id.streetInput).setText(address.street)
        dialogView.findViewById<TextInputEditText>(R.id.cityInput).setText(address.city)
        dialogView.findViewById<TextInputEditText>(R.id.postalCodeInput).setText(address.postalCode)
        dialogView.findViewById<TextInputEditText>(R.id.phoneInput).setText(address.phone)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Edit Address")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                // Get updated values
                val name = dialogView.findViewById<TextInputEditText>(R.id.nameInput).text.toString()
                val street = dialogView.findViewById<TextInputEditText>(R.id.streetInput).text.toString()
                val city = dialogView.findViewById<TextInputEditText>(R.id.cityInput).text.toString()
                val postalCode = dialogView.findViewById<TextInputEditText>(R.id.postalCodeInput).text.toString()
                val phone = dialogView.findViewById<TextInputEditText>(R.id.phoneInput).text.toString()

                if (validateAddress(name, street, city, postalCode, phone)) {
                    // Remove old address and add updated one
                    AddressManager.removeAddress(address)
                    val updatedAddress = Address(
                        id = address.id,
                        name = name,
                        street = street,
                        city = city,
                        postalCode = postalCode,
                        phone = phone,
                        isDefault = address.isDefault
                    )
                    AddressManager.addAddress(updatedAddress)
                    Toast.makeText(context, "Address updated", Toast.LENGTH_SHORT).show()
                    showAddressSelection()
                } else {
                    Toast.makeText(context, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
                }
            }
            .setNeutralButton("Delete") { _, _ ->
                AddressManager.removeAddress(address)
                Toast.makeText(context, "Address deleted", Toast.LENGTH_SHORT).show()
                showAddressSelection()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showNewAddressDialog() {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_shipping_address, null)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add New Address")
            .setView(dialogView)
            .setPositiveButton("Save") { dialog, _ ->
                val name = dialogView.findViewById<TextInputEditText>(R.id.nameInput).text.toString()
                val street = dialogView.findViewById<TextInputEditText>(R.id.streetInput).text.toString()
                val city = dialogView.findViewById<TextInputEditText>(R.id.cityInput).text.toString()
                val postalCode = dialogView.findViewById<TextInputEditText>(R.id.postalCodeInput).text.toString()
                val phone = dialogView.findViewById<TextInputEditText>(R.id.phoneInput).text.toString()

                if (validateAddress(name, street, city, postalCode, phone)) {
                    val newAddress = Address(
                        id = System.currentTimeMillis().toString(), // Generate a unique ID
                        name = name,
                        street = street,
                        city = city,
                        postalCode = postalCode,
                        phone = phone,
                        isDefault = false
                    )
                    AddressManager.addAddress(newAddress)
                    selectedAddress = newAddress
                    Toast.makeText(context, "Address saved", Toast.LENGTH_SHORT).show()
                    showPaymentSelection()
                } else {
                    Toast.makeText(context, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
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

    private fun showPaymentSelection() {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_bank_details, null)

        // Setup card number formatting and detection
        val cardNumberInput = dialogView.findViewById<TextInputEditText>(R.id.cardNumberInput)
        val cardTypeText = dialogView.findViewById<TextView>(R.id.cardTypeText)
        val cardTypeIcon = dialogView.findViewById<ImageView>(R.id.cardTypeIcon)
        val expiryDateInput = dialogView.findViewById<TextInputEditText>(R.id.expiryDateInput)

        cardNumberInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val number = s.toString()
                cardNumberInput.removeTextChangedListener(this)
                cardNumberInput.setText(CardFormatter.formatCardNumber(number))
                cardNumberInput.setSelection(cardNumberInput.text?.length ?: 0)
                cardNumberInput.addTextChangedListener(this)

                val cardType = CardFormatter.detectCardType(number)
                cardTypeText.text = cardType
                cardTypeIcon.setImageResource(CardFormatter.getCardIcon(cardType))
            }
        })

        expiryDateInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()
                expiryDateInput.removeTextChangedListener(this)
                expiryDateInput.setText(CardFormatter.formatExpiryDate(input))
                expiryDateInput.setSelection(expiryDateInput.text?.length ?: 0)
                expiryDateInput.addTextChangedListener(this)
            }
        })

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Payment Details")
            .setView(dialogView)
            .setPositiveButton("Pay") { _, _ ->
                // Process payment and show confirmation
                val orderId = generateOrderId()
                CartManager.clearCart()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, OrderConfirmationFragment.newInstance(orderId))
                    .commit()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun generateOrderId(): String {
        return "BF${System.currentTimeMillis().toString().takeLast(6)}"
    }
} 