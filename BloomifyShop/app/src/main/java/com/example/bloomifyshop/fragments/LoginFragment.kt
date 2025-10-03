import com.example.bloomifyshop.utils.UserManager

// In your login success handler
private fun handleLoginSuccess(email: String, password: String) {
    // Assuming you get the name from your login response
    val name = "User Name" // Replace with actual name from login response
    UserManager.setUserInfo(name, email)
    
    // Navigate to main screen
    // ... your existing navigation code ...
} 