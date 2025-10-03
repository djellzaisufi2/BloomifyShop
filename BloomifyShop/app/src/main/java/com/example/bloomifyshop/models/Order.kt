package com.example.bloomifyshop.models

data class Order(
    val id: String,
    val date: String,
    val items: List<OrderItem>,
    val total: Double,
    val status: String
)

data class OrderItem(
    val flower: Flower,
    val quantity: Int
) 