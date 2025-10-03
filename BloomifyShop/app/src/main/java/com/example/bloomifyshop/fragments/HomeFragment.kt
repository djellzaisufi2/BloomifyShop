package com.example.bloomifyshop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bloomifyshop.MainActivity
import com.example.bloomifyshop.R
import com.example.bloomifyshop.adapters.FlowerAdapter
import com.example.bloomifyshop.models.Flower

class HomeFragment : Fragment() {
    
    private lateinit var popularFlowersRecycler: RecyclerView
    private lateinit var specialOffersRecycler: RecyclerView
    private var allFlowers = listOf<Flower>()
    private var filteredFlowers = listOf<Flower>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerViews(view)
        loadDummyData()
    }

    private fun setupRecyclerViews(view: View) {
        popularFlowersRecycler = view.findViewById(R.id.popular_flowers_recycler)
        specialOffersRecycler = view.findViewById(R.id.special_offers_recycler)

        popularFlowersRecycler.layoutManager = 
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        specialOffersRecycler.layoutManager = 
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
    }

    private fun loadDummyData() {
        allFlowers = listOf(
            // Popular Flowers
            Flower(
                "Red Rose Bouquet",
                "Our premium red roses are carefully hand-selected for their vibrant color, rich fragrance, and perfect bloom stage. Each rose features layers of velvety petals that unfold gracefully, revealing a deep crimson heart. These roses are grown in specialized greenhouses that maintain optimal temperature and humidity levels to ensure the highest quality blooms. Perfect for romantic occasions, anniversaries, or expressing deep affection. Each bouquet contains 12-15 stems arranged with fresh seasonal greenery.",
                29.99,
                R.drawable.buqeta11,
                4.8f,
                careInstructions = "Water daily with room temperature water, ensuring the vase is filled to two-thirds. Trim stems at a 45-degree angle every 2-3 days using clean, sharp scissors to promote better water uptake. Remove any leaves that fall below the waterline to prevent bacterial growth. Keep away from direct sunlight, heating/cooling vents, and ripening fruit. Add flower food to the water and replace water every 2 days. Mist flowers lightly in dry environments. Remove spent blooms to encourage remaining flowers to last longer.",
                bloomSeason = "Year-round, with peak quality in spring and early summer. Our greenhouse cultivation ensures consistent quality across seasons.",
                wateringNeeds = "Daily watering with clean, room temperature water. Check water level twice daily in warm conditions."
            ),
            Flower(
                "Pink Orchid",
                "These stunning exotic Phalaenopsis orchids feature delicate pink blooms with intricate patterns and subtle color variations. Each plant produces multiple flower spikes with 8-12 long-lasting blooms that can persist for 2-3 months. Their elegant butterfly-like flowers cascade gracefully from strong, upright stems. The thick, glossy leaves provide a beautiful contrast to the delicate blooms. Perfect for home decor, office spaces, or as sophisticated gifts. Each plant comes in a decorative ceramic pot with proper drainage.",
                49.99,
                R.drawable.pink_orchid,
                4.9f,
                careInstructions = "Place in bright, indirect light - avoid direct sunlight which can burn leaves. Water weekly by soaking the roots for 10-15 minutes in room temperature water, then drain completely. Never let roots sit in standing water. Maintain humidity by misting or using a humidity tray filled with pebbles and water. Feed with specialized orchid fertilizer monthly during growing season (spring/summer), reducing to every other month in winter. Repot every 1-2 years with fresh orchid mix. Remove spent blooms at the base of the flower spike. Only cut flower spikes back when they've completely browned.",
                bloomSeason = "Can bloom multiple times per year with proper care. Primary blooming season is late winter through spring.",
                wateringNeeds = "Weekly soaking, allow to dry between waterings. Increase frequency in summer and decrease in winter."
            ),
            Flower(
                "Sunflower Bundle",
                "Our cheerful sunflower bundles bring the warmth of summer into any space. Each stem is carefully selected for its large, vibrant bloom and strong stem, featuring flowers 6-8 inches in diameter. These impressive flowers naturally track the sun throughout the day and create an instant focal point in any room. The deep golden petals surround rich chocolate-colored centers filled with nutritious seeds. Each bundle includes 5-7 stems at varying heights (24-36 inches) for dramatic arrangement possibilities. Perfect for brightening spaces, summer celebrations, or rustic-themed events.",
                24.99,
                R.drawable.sun_flowe,
                4.7f,
                careInstructions = "Place in a sunny location that receives 6-8 hours of direct sunlight daily. Change water daily and add flower food for longevity. Trim stems at an angle every 2 days using sharp scissors and remove any leaves that fall below the water line. Keep away from drafts and air conditioning vents. Support heavy blooms with proper stem placement or floral tape if needed. To prevent pollen drop, gently remove the stamens once flowers open fully. For longest vase life, choose flowers that are just beginning to open.",
                bloomSeason = "Late summer to early fall, peak season July-September. Greenhouse varieties available in other seasons.",
                wateringNeeds = "Daily water changes with fresh flower food. Use warm water for initial stem cutting, then maintain with room temperature water."
            ),
            Flower(
                "Lavender Plant",
                "Our English Lavender (Lavandula angustifolia) plants offer both visual beauty and therapeutic aromatic benefits. Each mature plant produces dozens of fragrant purple bloom spikes rising above silvery-green foliage. The compact growth habit reaches 18-24 inches in height and spread. Known for their calming properties, these plants produce abundant flowers rich in essential oils. Perfect for gardens, containers, or indoor spaces where their soothing scent can be appreciated. Excellent for attracting pollinators and creating dried arrangements.",
                19.99,
                R.drawable.lavender_plant,
                4.6f,
                careInstructions = "Plant in well-draining soil in full sun location. Add lime if soil is acidic - lavender prefers slightly alkaline conditions. Prune after first bloom to encourage second flowering and maintain shape, cutting back by about one-third. Avoid overwatering - lavender prefers slightly dry conditions and is drought-tolerant once established. Provide good air circulation to prevent fungal issues. Cut back by one-third in late summer to prepare for winter. Mulch with gravel or sand rather than organic materials to prevent root rot. Harvest flower spikes just as buds begin to open for maximum fragrance.",
                bloomSeason = "Main bloom in late spring, with potential second bloom in late summer if properly pruned. Flowers can be dried for year-round use.",
                wateringNeeds = "Moderate, allow soil to dry between waterings. Reduce watering in winter months."
            ),
            // Special Offers
            Flower(
                "Mixed Tulip Bouquet",
                "A stunning array of premium tulips in a rainbow of colors, each stem carefully selected for quality and longevity. This spring favorite brings together vibrant reds, sunny yellows, deep purples, and soft pinks to create a spectacular display. Each bouquet contains 20 stems featuring both single and double varieties, with large, cup-shaped blooms atop strong stems. The tulips are cut at the perfect stage to open gradually in your home, providing an evolving display that lasts 7-10 days. Each bouquet is uniquely arranged to showcase the natural beauty of these seasonal favorites.",
                34.99,
                R.drawable.mixed_tulip,
                4.5f,
                careInstructions = "Keep in a cool place (60-65°F) away from direct sunlight and heat sources. Change water every 2 days with fresh, cold water. Add flower food with each water change. Trim stems at an angle every 3 days. Remove any leaves that fall below the water line. These flowers continue to grow after cutting, so account for additional height in your arrangement. To prevent stems from bending, wrap the upper portion of the stems in paper when the lights are off - tulips grow toward light sources. Remove paper during daytime.",
                bloomSeason = "Early to late spring, with greenhouse varieties available year-round. Peak season is March through May.",
                wateringNeeds = "Every 2 days with fresh, cold water. Tulips are thirsty flowers and benefit from deep water."
            ),
            Flower(
                "Peace Lily Special",
                "An elegant peace lily (Spathiphyllum) plant featuring glossy dark green leaves and pristine white flowers. This air-purifying powerhouse not only adds sophistication to any space but also helps clean indoor air of common pollutants including benzene, formaldehyde, and carbon monoxide. The plant reaches 24-36 inches in height and produces multiple blooms throughout the year. Each plant comes in a decorative pot with a water indicator to help maintain proper moisture levels. Ideal for both home and office environments, especially in areas with limited natural light.",
                29.99,
                R.drawable.special_lily,
                4.8f,
                careInstructions = "Place in medium to low indirect light - avoid direct sunlight which can burn leaves. Water when top inch of soil feels dry, or when leaves begin to slightly droop. Use filtered water if possible as peace lilies are sensitive to fluoride. Mist regularly to maintain humidity or place on a pebble tray with water. Clean leaves monthly with damp cloth to maintain glossy appearance and help the plant photosynthesize efficiently. Feed with balanced houseplant fertilizer (10-10-10) every 6-8 weeks during growing season. Divide and repot when plant becomes crowded, typically every 2-3 years.",
                bloomSeason = "Can bloom throughout the year with proper care, with heaviest flowering in spring and early summer.",
                wateringNeeds = "Weekly or when soil feels dry to touch. Prefers consistent moisture but not waterlogged soil."
            ),
            Flower(
                "Garden Starter Kit",
                "A comprehensive gardening kit designed for both beginners and experienced gardeners. Includes a curated selection of seasonal flower seeds (zinnias, cosmos, marigolds, and wildflowers), biodegradable seed starting pots, premium organic potting soil, plant markers, a spray bottle, and detailed growing guides. The kit also features essential gardening tools including pruning shears, a hand trowel, and gardening gloves. Perfect for starting a flower garden or giving as a gift to aspiring gardeners.",
                39.99,
                R.drawable.garden,
                4.4f,
                careInstructions = "Follow individual care guides for each flower variety included. Generally, start seeds indoors 6-8 weeks before last frost date. Maintain soil temperature at 70-75°F for germination. Provide 14-16 hours of light daily using grow lights or sunny windowsill. Water gently to maintain consistent moisture during germination. Harden off seedlings before transplanting outdoors. Space plants according to specific variety requirements.",
                bloomSeason = "Varies by included plants, with succession planting guide for continuous blooms throughout growing season.",
                wateringNeeds = "Varies by plant type and growth stage. Detailed watering schedule included in care guide."
            ),
            Flower(
                "Anniversary Special",
                "A luxurious celebration bouquet featuring a stunning combination of premium roses and oriental lilies. This elegant arrangement includes one dozen long-stemmed red roses, complemented by three stems of fragrant oriental lilies with multiple blooms per stem. The bouquet is expertly arranged with eucalyptus and salal greens for a full, professional presentation. Includes a premium glass vase with geometric detailing and a personalized message card. The combination of roses and lilies ensures the arrangement will continue to evolve and impress as the lilies open over several days.",
                59.99,
                R.drawable.anniversary,
                4.9f,
                careInstructions = "Change water daily using room temperature water and flower food. Remove lily stamens as flowers open to prevent pollen stains. Trim stems at 45-degree angle daily using clean scissors. Keep away from direct sunlight and heat sources. Remove spent blooms to maintain appearance. For lilies, remove outer petals if they begin to fade while center petals are still fresh. Position arrangement away from ripening fruit and drafts. Re-cut rose stems underwater to prevent air bubbles that can block water uptake.",
                bloomSeason = "Available year-round through our global grower network, ensuring consistent quality.",
                wateringNeeds = "Daily water changes required. Monitor water level twice daily as lilies are particularly thirsty flowers."
            )
        )
        filteredFlowers = allFlowers
        updateRecyclerViews()
    }

    fun searchFlowers(query: String) {
        if (query.isEmpty()) {
            filteredFlowers = allFlowers
        } else {
            filteredFlowers = allFlowers.filter { flower ->
                flower.name.contains(query, ignoreCase = true) ||
                flower.description.contains(query, ignoreCase = true)
            }
        }
        updateRecyclerViews()
    }

    private fun updateRecyclerViews() {
        // Update Popular Flowers section
        popularFlowersRecycler.adapter = FlowerAdapter(
            filteredFlowers.take(4)
        ) { flower ->
            val fragment = FlowerDetailFragment.newInstance(flower)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
            
            (activity as? MainActivity)?.loadFragment(fragment)
        }

        // Update Special Offers section
        specialOffersRecycler.adapter = FlowerAdapter(
            filteredFlowers.takeLast(4)
        ) { flower ->
            val fragment = FlowerDetailFragment.newInstance(flower)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
            
            (activity as? MainActivity)?.loadFragment(fragment)
        }
    }
} 