package com.example.bloomifyshop.models

data class Item(
    val title: String = "",
    val description: String = "",
    val picUrl: List<String> = listOf(),
    val price: Double = 0.0,
    val rating: Double = 0.0,
    val careInstructions: String = "",
    val bloomSeason: String? = null,
    val wateringNeeds: String? = null,
    val sunlightNeeds: String? = null,
    val occasionTags: List<String> = listOf(),
    val size: List<String> = listOf(),
    val categoryId: String = ""
) 