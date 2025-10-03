package com.example.bloomifyshop

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bloomifyshop.fragments.*  // This will import all fragments
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var searchInput: EditText
    private lateinit var bannerImage: ImageView
    private lateinit var searchCard: View
    private lateinit var headerContainer: View
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bannerImage = findViewById(R.id.bannerImage)
        searchCard = findViewById(R.id.searchCard)
        headerContainer = findViewById(R.id.headerContainer)
        setupBottomNavigation()
        setupSearch()

        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_categories -> {
                    loadFragment(CategoriesFragment())
                    true
                }
                R.id.nav_cart -> {
                    loadFragment(CartFragment())
                    true
                }
                R.id.nav_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun setupSearch() {
        searchInput = findViewById(R.id.searchInput)
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                when (val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)) {
                    is HomeFragment -> fragment.searchFlowers(query)
                    is CategoryItemsFragment -> fragment.searchItems(query)
                }
            }
        })
    }

    fun loadFragment(fragment: Fragment) {
        currentFragment = fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        
        val showHeaderElements = when (fragment) {
            is HomeFragment -> true
            is FlowerDetailFragment -> false
            else -> false
        }
        
        headerContainer.visibility = if (showHeaderElements) View.VISIBLE else View.GONE
        
        // Clear search when switching fragments
        searchInput.text.clear()
    }
}