package com.example.bloomifyshop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bloomifyshop.R
import com.example.bloomifyshop.adapters.OrderAdapter
import com.example.bloomifyshop.models.Order
import com.example.bloomifyshop.models.OrderItem
import com.example.bloomifyshop.models.Flower

class OrderHistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBackButton(view)
        setupRecyclerView(view)
    }

    private fun setupBackButton(view: View) {
        view.findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.ordersRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = OrderAdapter(getDummyOrders())
    }

    private fun getDummyOrders(): List<Order> {
        return listOf(
            Order(
                id = "ORD-001",
                date = "March 15, 2024",
                items = listOf(
                    OrderItem(
                        flower = Flower(
                            name = "Red Rose Bouquet",
                            description = "Premium red roses hand-selected for their vibrant color and rich fragrance.",
                            price = 29.99,
                            imageResource = R.drawable.buqeta11,
                            rating = 4.8f
                        ),
                        quantity = 2
                    ),
                    OrderItem(
                        flower = Flower(
                            name = "White Lily",
                            description = "Elegant white lilies symbolizing purity and refined beauty.",
                            price = 34.99,
                            imageResource = R.drawable.white_lily1,
                            rating = 4.7f
                        ),
                        quantity = 1
                    )
                ),
                total = 94.97,
                status = "Delivered"
            ),
            Order(
                id = "ORD-002",
                date = "March 10, 2024",
                items = listOf(
                    OrderItem(
                        flower = Flower(
                            name = "Pink Orchid",
                            description = "Exotic orchids in various colors with long-lasting blooms.",
                            price = 49.99,
                            imageResource = R.drawable.pink_orchid,
                            rating = 4.9f
                        ),
                        quantity = 1
                    ),
                    OrderItem(
                        flower = Flower(
                            name = "Crystal Vase",
                            description = "Elegant crystal vase with cut pattern.",
                            price = 39.99,
                            imageResource = R.drawable.crystal_vase,
                            rating = 4.6f
                        ),
                        quantity = 1
                    )
                ),
                total = 89.98,
                status = "Processing"
            )
        )
    }
} 