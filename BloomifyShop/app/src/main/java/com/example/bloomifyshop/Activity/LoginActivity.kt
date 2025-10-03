package com.example.bloomifyshop.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bloomifyshop.MainActivity
import com.example.bloomifyshop.R
import com.example.bloomifyshop.utils.UserManager
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signupText = findViewById<TextView>(R.id.signupText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val emailInput = findViewById<TextInputEditText>(R.id.emailInput)
        val passwordInput = findViewById<TextInputEditText>(R.id.passwordInput)

        signupText.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (validateInput(email, password)) {
                // Save user info with just the name from email
                UserManager.setUserInfo(email.substringBefore('@'), email)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty() || password.isEmpty()) {
            // Show error message
            return false
        }
        return true
    }
} 