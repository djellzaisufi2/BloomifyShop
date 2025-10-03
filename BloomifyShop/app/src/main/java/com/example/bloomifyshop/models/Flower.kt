package com.example.bloomifyshop.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flower(
    val name: String,
    val description: String,
    val price: Double,
    val imageResource: Int,
    val rating: Float = 0f,
    val careInstructions: String? = null,
    val bloomSeason: String? = null,
    val wateringNeeds: String? = null
) : Parcelable 