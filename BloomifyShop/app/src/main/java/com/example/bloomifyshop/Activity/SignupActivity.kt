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

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val loginText = findViewById<TextView>(R.id.loginText)
        val signupButton = findViewById<Button>(R.id.signupButton)
        val nameInput = findViewById<TextInputEditText>(R.id.nameInput)
        val emailInput = findViewById<TextInputEditText>(R.id.emailInput)
        val passwordInput = findViewById<TextInputEditText>(R.id.passwordInput)
        val confirmPasswordInput = findViewById<TextInputEditText>(R.id.confirmPasswordInput)

        loginText.setOnClickListener {
            finish()
        }

        signupButton.setOnClickListener {
            val name = nameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val confirmPassword = confirmPasswordInput.text.toString()

            if (validateInput(name, email, password, confirmPassword)) {
                // Save user info with the actual name
                UserManager.setUserInfo(name, email)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun validateInput(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            // Show error message
            return false
        }
        if (password != confirmPassword) {
            // Show password mismatch error
            return false
        }
        return true
    }
} 