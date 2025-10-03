package com.example.bloomifyshop.utils

import com.example.bloomifyshop.R

object CardFormatter {
    fun formatCardNumber(number: String): String {
        val digits = number.filter { it.isDigit() }
        return digits.chunked(4).joinToString(" ")
    }

    fun detectCardType(number: String): String {
        val firstDigit = number.firstOrNull()
        val firstTwoDigits = number.take(2).toIntOrNull()

        return when {
            firstDigit == '4' -> "Visa"
            firstTwoDigits in 51..55 -> "MasterCard"
            firstTwoDigits == 34 || firstTwoDigits == 37 -> "American Express"
            else -> "Unknown"
        }
    }

    fun formatExpiryDate(input: String): String {
        val digits = input.filter { it.isDigit() }
        return when {
            digits.length <= 2 -> digits
            else -> "${digits.take(2)}/${digits.drop(2)}"
        }
    }

    fun getCardIcon(cardType: String): Int {
        return when (cardType) {
            "Visa" -> R.drawable.ic_visa
            "MasterCard" -> R.drawable.ic_mastercard
            else -> 0
        }
    }
} 