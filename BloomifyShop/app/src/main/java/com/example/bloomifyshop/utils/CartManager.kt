package com.example.bloomifyshop.utils

import com.example.bloomifyshop.models.CartItem

object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun addToCart(item: CartItem) {
        cartItems.add(item)
    }

    fun getCartItems(): List<CartItem> = cartItems.toList()

    fun clearCart() {
        cartItems.clear()
    }

    fun removeItem(item: CartItem) {
        cartItems.remove(item)
    }
} 