package com.example.bloomifyshop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bloomifyshop.R
import com.example.bloomifyshop.adapters.FlowerAdapter
import com.example.bloomifyshop.models.Flower

class CategoryItemsFragment : Fragment() {

    private lateinit var itemsRecycler: RecyclerView
    private lateinit var categoryTitle: TextView
    private var categoryId: String = ""
    private var allItems = listOf<Flower>()
    private var filteredItems = listOf<Flower>()

    companion object {
        private const val ARG_CATEGORY_ID = "category_id"

        fun newInstance(categoryId: String): CategoryItemsFragment {
            return CategoryItemsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CATEGORY_ID, categoryId)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryId = it.getString(ARG_CATEGORY_ID, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupViews(view)
        loadItems()
    }

    private fun setupViews(view: View) {
        itemsRecycler = view.findViewById(R.id.categoryItemsRecycler)
        categoryTitle = view.findViewById(R.id.categoryTitle)
        itemsRecycler.layoutManager = GridLayoutManager(context, 2)

        // Set category title
        categoryTitle.text = when (categoryId) {
            "flowers" -> "Fresh Flowers"
            "plants" -> "Indoor Plants"
            "bouquets" -> "Bouquets"
            "vases" -> "Vases"
            else -> "Items"
        }
    }

    private fun loadItems() {
        allItems = getDummyItems()
        filteredItems = allItems
        updateRecyclerView()
    }

    private fun updateRecyclerView() {
        itemsRecycler.adapter = FlowerAdapter(filteredItems) { flower ->
            val fragment = FlowerDetailFragment.newInstance(flower)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    fun searchItems(query: String) {
        if (query.isEmpty()) {
            filteredItems = allItems
        } else {
            filteredItems = allItems.filter { item ->
                item.name.contains(query, ignoreCase = true) ||
                item.description.contains(query, ignoreCase = true)
            }
        }
        updateRecyclerView()
    }

    private fun getDummyItems(): List<Flower> {
        return when (categoryId) {
            "flowers" -> listOf(
                Flower(
                    name = "Red Rose",
                    description = "Beautiful fresh red roses, perfect for romantic occasions.",
                    price = 29.99,
                    imageResource = R.drawable.rose_flower,
                    rating = 4.5f,
                    careInstructions = "Water daily, keep in sunlight, trim stems at 45-degree angle",
                    bloomSeason = "Year-round",
                    wateringNeeds = "Daily"
                ),
                Flower(
                    name = "White Lily",
                    description = "Elegant white lilies symbolizing purity and refined beauty.",
                    price = 34.99,
                    imageResource = R.drawable.white_lily1,
                    rating = 4.7f,
                    careInstructions = "Keep soil moist, avoid direct sunlight, remove pollen",
                    bloomSeason = "Summer",
                    wateringNeeds = "Every 2-3 days"
                ),
                Flower(
                    name = "Pink Peony",
                    description = "Luxurious pink peonies with full, ruffled blooms.",
                    price = 39.99,
                    imageResource = R.drawable.pink_poeny,
                    rating = 4.8f,
                    careInstructions = "Plant in full sun, provide support for heavy blooms, water at base to prevent fungal issues.",
                    bloomSeason = "Late Spring to Early Summer",
                    wateringNeeds = "Moderate, keep soil moist"
                ),
                Flower(
                    name = "Purple Iris",
                    description = "Striking purple iris with unique petal patterns.",
                    price = 24.99,
                    imageResource = R.drawable.purple_iris,
                    rating = 4.4f,
                    careInstructions = "Plant in well-draining soil, divide every 3-4 years, remove dead foliage.",
                    bloomSeason = "Spring",
                    wateringNeeds = "Weekly"
                ),
                Flower(
                    name = "Yellow Daffodil",
                    description = "Cheerful yellow daffodils that herald the arrival of spring.",
                    price = 19.99,
                    imageResource = R.drawable.yellow_dadalies,
                    rating = 4.3f,
                    careInstructions = "Plant bulbs in fall, let foliage die back naturally, avoid overwatering when dormant.",
                    bloomSeason = "Early Spring",
                    wateringNeeds = "Moderate during growing season"
                ),
                Flower(
                    name = "Blue Hydrangea",
                    description = "Stunning blue hydrangea with large, showy flower heads.",
                    price = 44.99,
                    imageResource = R.drawable.blue_hydragegau,
                    rating = 4.9f,
                    careInstructions = "Morning sun and afternoon shade, keep soil consistently moist, protect from harsh winds.",
                    bloomSeason = "Summer to Fall",
                    wateringNeeds = "Regular, don't let soil dry out"
                )
            )
            "plants" -> listOf(
                Flower(
                    name = "Peace Lily",
                    description = "Air-purifying indoor plant with elegant white flowers.",
                    price = 39.99,
                    imageResource = R.drawable.peace_lily,
                    rating = 4.8f,
                    careInstructions = "Keep in indirect light, water when top soil is dry",
                    bloomSeason = "Spring through Summer",
                    wateringNeeds = "Weekly"
                ),
                Flower(
                    name = "Snake Plant",
                    description = "Low-maintenance plant perfect for beginners.",
                    price = 24.99,
                    imageResource = R.drawable.snake_plant,
                    rating = 4.6f,
                    careInstructions = "Tolerates low light, avoid overwatering, can handle irregular watering.",
                    bloomSeason = "Rarely blooms indoors",
                    wateringNeeds = "Every 2-3 weeks"
                ),
                Flower(
                    name = "Monstera Deliciosa",
                    description = "Tropical plant with distinctive split leaves.",
                    price = 49.99,
                    imageResource = R.drawable.monstera,
                    rating = 4.7f,
                    careInstructions = "Bright indirect light, support for climbing, regular misting for humidity.",
                    bloomSeason = "Non-flowering indoor plant",
                    wateringNeeds = "Weekly, allow top soil to dry"
                ),
                Flower(
                    name = "Fiddle Leaf Fig",
                    description = "Popular indoor tree with large, violin-shaped leaves.",
                    price = 79.99,
                    imageResource = R.drawable.fiddle,
                    rating = 4.5f,
                    careInstructions = "Bright indirect light, consistent watering, rotate regularly for even growth.",
                    bloomSeason = "Non-flowering",
                    wateringNeeds = "When top 1-inch soil is dry"
                )
            )
            "bouquets" -> listOf(
                Flower(
                    name = "Romance Bundle",
                    description = "Luxurious mix of roses, lilies, and carnations in romantic pink and red hues.",
                    price = 59.99,
                    imageResource = R.drawable.romance_bundle,
                    rating = 4.7f,
                    careInstructions = "Change water daily, trim stems, keep away from heat sources and direct sunlight.",
                    bloomSeason = "Available year-round",
                    wateringNeeds = "Daily water change"
                ),
                Flower(
                    name = "Spring Medley",
                    description = "Vibrant mix of tulips, daffodils, and hyacinths.",
                    price = 49.99,
                    imageResource = R.drawable.spring,
                    rating = 4.5f,
                    careInstructions = "Keep in cool place, change water every 2 days, trim stems at an angle.",
                    bloomSeason = "Spring",
                    wateringNeeds = "Every 2 days"
                ),
                Flower(
                    name = "Tropical Paradise",
                    description = "Exotic arrangement with birds of paradise, orchids, and tropical foliage.",
                    price = 79.99,
                    imageResource = R.drawable.paradise,
                    rating = 4.8f,
                    careInstructions = "Mist daily, keep away from cold drafts, use flower food in water.",
                    bloomSeason = "Available year-round",
                    wateringNeeds = "Daily misting and water change"
                ),
                Flower(
                    name = "Rustic Charm",
                    description = "Wildflower-inspired arrangement with sunflowers, daisies, and seasonal fillers.",
                    price = 45.99,
                    imageResource = R.drawable.sunshine,
                    rating = 4.6f,
                    careInstructions = "Place in bright location, change water every 2-3 days, remove spent blooms.",
                    bloomSeason = "Summer to Fall",
                    wateringNeeds = "Every 2-3 days"
                )
            )
            "vases" -> listOf(
                Flower(
                    name = "Crystal Vase",
                    description = "Elegant crystal vase with cut pattern.",
                    price = 39.99,
                    imageResource = R.drawable.crystal_vase,
                    rating = 4.6f,
                    careInstructions = "Hand wash with mild soap, avoid extreme temperature changes",
                    bloomSeason = null,
                    wateringNeeds = null
                ),
                Flower(
                    name = "Modern Ceramic Pot",
                    description = "Contemporary ceramic pot with drainage hole.",
                    price = 29.99,
                    imageResource = R.drawable.modern,
                    rating = 4.4f,
                    careInstructions = "Dishwasher safe, suitable for indoor and outdoor use",
                    bloomSeason = null,
                    wateringNeeds = null
                ),
                Flower(
                    name = "Vintage Glass Collection",
                    description = "Set of three vintage-style glass vases in varying heights.",
                    price = 49.99,
                    imageResource = R.drawable.vintage,
                    rating = 4.7f,
                    careInstructions = "Hand wash recommended, careful handling required",
                    bloomSeason = null,
                    wateringNeeds = null
                ),
                Flower(
                    name = "Bamboo Planter",
                    description = "Eco-friendly bamboo planter with matching saucer.",
                    price = 34.99,
                    imageResource = R.drawable.bamboo_planter,
                    rating = 4.5f,
                    careInstructions = "Wipe clean with damp cloth, avoid prolonged water exposure",
                    bloomSeason = null,
                    wateringNeeds = null
                )
            )
            else -> emptyList()
        }
    }
} 