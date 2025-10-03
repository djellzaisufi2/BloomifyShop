package com.example.bloomifyshop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bloomifyshop.R
import com.example.bloomifyshop.adapters.CategoryAdapter
import com.example.bloomifyshop.models.Category

class CategoriesFragment : Fragment() {
    
    private lateinit var categoriesRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView(view)
        loadCategories()
    }

    private fun setupRecyclerView(view: View) {
        categoriesRecycler = view.findViewById(R.id.categories_recycler)
        categoriesRecycler.layoutManager = GridLayoutManager(context, 2)
    }

    private fun loadCategories() {
        val categories = listOf(
            Category(
                id = "flowers",
                imageResource = R.drawable.flowers_category,
                title = "Fresh Flowers"
            ),
            Category(
                id = "plants",
                imageResource = R.drawable.plants_category,
                title = "Indoor Plants"
            ),
            Category(
                id = "bouquets",
                imageResource = R.drawable.boqouts_category,
                title = "Bouquets"
            ),
            Category(
                id = "vases",
                imageResource = R.drawable.vases_category,
                title = "Vases"
            )
        )

        categoriesRecycler.adapter = CategoryAdapter(categories) { category ->
            val fragment = CategoryItemsFragment.newInstance(category.id)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
} 