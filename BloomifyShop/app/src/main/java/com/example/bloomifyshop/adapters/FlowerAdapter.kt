package com.example.bloomifyshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bloomifyshop.R
import com.example.bloomifyshop.models.Flower

class FlowerAdapter(
    private val flowers: List<Flower>,
    private val onItemClick: (Flower) -> Unit
) : RecyclerView.Adapter<FlowerAdapter.FlowerViewHolder>() {

    class FlowerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.flower_image)
        val nameText: TextView = view.findViewById(R.id.flower_name)
        val descriptionText: TextView = view.findViewById(R.id.flower_description)
        val priceText: TextView = view.findViewById(R.id.flower_price)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingBar)
        val addToCartButton: Button = view.findViewById(R.id.add_to_cart_button)
        val itemContainer: View = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_flower, parent, false)
        view.startAnimation(AnimationUtils.loadAnimation(parent.context, R.anim.item_animation_fall_down))
        return FlowerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        val flower = flowers[position]
        
        holder.imageView.setImageResource(flower.imageResource)
        holder.nameText.text = flower.name
        holder.descriptionText.text = flower.description
        holder.priceText.text = String.format("$%.2f", flower.price)
        holder.ratingBar.rating = flower.rating
        
        holder.itemContainer.setOnClickListener {
            onItemClick(flower)
        }
        
        holder.addToCartButton.setOnClickListener {
            onItemClick(flower)
        }
    }

    override fun getItemCount() = flowers.size
} 