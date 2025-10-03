package com.example.bloomifyshop.models

data class PaymentMethod(
    val id: String,
    val cardNumber: String,
    val cardHolder: String,
    val expiryDate: String,
    val type: String,
    val isDefault: Boolean = false
) 