package com.example.bloomifyshop.models

data class CartItem(
    val name: String,
    val price: Double,
    var quantity: Int,
    val imageResource: Int
) 