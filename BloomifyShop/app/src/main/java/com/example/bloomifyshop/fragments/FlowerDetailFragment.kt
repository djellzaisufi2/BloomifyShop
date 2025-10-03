package com.example.bloomifyshop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bloomifyshop.MainActivity
import com.example.bloomifyshop.R
import com.example.bloomifyshop.models.CartItem
import com.example.bloomifyshop.models.Flower
import com.example.bloomifyshop.utils.CartManager

class FlowerDetailFragment : Fragment() {

    private lateinit var flower: Flower
    private var quantity: Int = 1

    companion object {
        private const val ARG_FLOWER = "flower"

        fun newInstance(flower: Flower): FlowerDetailFragment {
            return FlowerDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_FLOWER, flower)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            flower = it.getParcelable(ARG_FLOWER)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_flower_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews(view)
        setupQuantityControls(view)
        setupBackButton(view)
    }

    private fun setupViews(view: View) {
        // Set main details
        view.findViewById<ImageView>(R.id.flowerImage).setImageResource(flower.imageResource)
        view.findViewById<TextView>(R.id.flowerName).text = flower.name
        view.findViewById<TextView>(R.id.flowerPrice).text = String.format("$%.2f", flower.price)
        view.findViewById<TextView>(R.id.flowerDescription).text = flower.description

        // Set rating
        view.findViewById<android.widget.RatingBar>(R.id.ratingBar).rating = flower.rating

        // Set care instructions if available
        flower.careInstructions?.let { instructions ->
            view.findViewById<TextView>(R.id.careInstructions).text = instructions
        } ?: run {
            view.findViewById<View>(R.id.careInstructionsLayout).visibility = View.GONE
        }

        // Set growing details if available
        val detailsLayout = view.findViewById<View>(R.id.flowerDetailsLayout)
        if (flower.bloomSeason != null || flower.wateringNeeds != null) {
            flower.bloomSeason?.let {
                view.findViewById<TextView>(R.id.bloomSeason).text = it
            }
            flower.wateringNeeds?.let {
                view.findViewById<TextView>(R.id.wateringNeeds).text = it
            }
        } else {
            detailsLayout.visibility = View.GONE
        }

        // Setup add to cart button
        view.findViewById<Button>(R.id.addToCartButton).setOnClickListener {
            addToCart()
        }
    }

    private fun setupQuantityControls(view: View) {
        val quantityText = view.findViewById<TextView>(R.id.quantityTxt)
        val minusButton = view.findViewById<ImageButton>(R.id.minusBtn)
        val plusButton = view.findViewById<ImageButton>(R.id.plusBtn)

        minusButton.setOnClickListener {
            if (quantity > 1) {
                quantity--
                updateQuantityDisplay(quantityText)
            }
        }

        plusButton.setOnClickListener {
            quantity++
            updateQuantityDisplay(quantityText)
        }
    }

    private fun setupBackButton(view: View) {
        view.findViewById<ImageView>(R.id.backBtn).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun updateQuantityDisplay(quantityText: TextView) {
        quantityText.text = quantity.toString()
    }

    private fun addToCart() {
        val cartItem = CartItem(
            name = flower.name,
            price = flower.price,
            quantity = quantity,
            imageResource = flower.imageResource
        )
        
        CartManager.addToCart(cartItem)
        Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show()
    }
} 