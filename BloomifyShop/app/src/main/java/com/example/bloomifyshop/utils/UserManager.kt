package com.example.bloomifyshop.utils

object UserManager {
    private var name: String = ""
    private var email: String = ""

    fun setUserInfo(name: String, email: String) {
        this.name = name
        this.email = email
    }

    fun getUserName(): String = name
    fun getUserEmail(): String = email
} 