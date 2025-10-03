package com.example.bloomifyshop.models

data class Address(
    val id: String = "",
    val name: String,
    val street: String,
    val city: String,
    val postalCode: String,
    val phone: String,
    val isDefault: Boolean = false
) 