package com.example.bloomifyshop.utils

import com.example.bloomifyshop.models.Address

object AddressManager {
    private val addresses = mutableListOf<Address>()

    init {
        // Add some initial addresses
        addAddress(
            Address(
                id = "1",
                name = UserManager.getUserName(),
                street = "123 Main Street",
                city = "New York",
                postalCode = "10001",
                phone = "(555) 123-4567",
                isDefault = true
            )
        )
    }

    fun addAddress(address: Address) {
        // If this is set as default, remove default from others
        if (address.isDefault) {
            addresses.forEach { existingAddress ->
                if (existingAddress.isDefault) {
                    addresses[addresses.indexOf(existingAddress)] = existingAddress.copy(isDefault = false)
                }
            }
        }
        addresses.add(address)
    }

    fun getAddresses(): List<Address> = addresses.toList()

    fun removeAddress(address: Address) {
        addresses.remove(address)
    }

    fun updateAddress(oldAddress: Address, newAddress: Address) {
        val index = addresses.indexOf(oldAddress)
        if (index != -1) {
            addresses[index] = newAddress
        }
    }

    fun clearAddresses() {
        addresses.clear()
    }
} 