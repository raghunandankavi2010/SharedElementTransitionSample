package com.example.myapp.data

import androidx.compose.runtime.Immutable

@Immutable
data class Item(
    val id: Int,
    val title: String,
    val subtitle: String,
    val description: String,
    val imageUrl: String,
    val category: String,
    val isFavorite: Boolean = false
)

object SampleData {
    val items = listOf(
        Item(
            id = 1,
            title = "Mountain Adventure",
            subtitle = "Explore the peaks",
            description = "Experience the thrill of mountain climbing with our expert guides. " +
                    "This adventure takes you through breathtaking landscapes and challenging trails. " +
                    "Perfect for nature lovers and adventure seekers alike.",
            imageUrl = "https://fastly.picsum.photos/id/10/800/450.jpg?hmac=X-m6LqWzK_M7pW_Lp-oP1f_N_G_T_V_S_H_I_N_E",
            category = "Adventure"
        ),
        Item(
            id = 2,
            title = "Ocean Paradise",
            subtitle = "Dive into blue",
            description = "Discover the wonders of the ocean with our diving expeditions. " +
                    "Swim alongside colorful marine life and explore coral reefs. " +
                    "An unforgettable underwater experience awaits you.",
            imageUrl = "https://fastly.picsum.photos/id/11/800/450.jpg?hmac=X-m6LqWzK_M7pW_Lp-oP1f_N_G_T_V_S_H_I_N_E",
            category = "Travel"
        ),
        Item(
            id = 3,
            title = "Urban Exploration",
            subtitle = "City lights",
            description = "Navigate through bustling city streets and discover hidden gems. " +
                    "From rooftop bars to underground art scenes, experience the city like never before. " +
                    "A perfect blend of culture, cuisine, and nightlife.",
            imageUrl = "https://fastly.picsum.photos/id/12/800/450.jpg?hmac=X-m6LqWzK_M7pW_Lp-oP1f_N_G_T_V_S_H_I_N_E",
            category = "City"
        ),
        Item(
            id = 4,
            title = "Forest Retreat",
            subtitle = "Peaceful escape",
            description = "Reconnect with nature in our serene forest retreat. " +
                    "Wake up to birdsong and fall asleep under a canopy of stars. " +
                    "Ideal for meditation, yoga, and digital detox.",
            imageUrl = "https://fastly.picsum.photos/id/13/800/450.jpg?hmac=X-m6LqWzK_M7pW_Lp-oP1f_N_G_T_V_S_H_I_N_E",
            category = "Nature"
        ),
        Item(
            id = 5,
            title = "Desert Safari",
            subtitle = "Golden dunes",
            description = "Ride across golden sand dunes and witness stunning sunsets. " +
                    "Experience Bedouin hospitality and traditional desert culture. " +
                    "A magical journey through the heart of the desert.",
            imageUrl = "https://fastly.picsum.photos/id/14/800/450.jpg?hmac=X-m6LqWzK_M7pW_Lp-oP1f_N_G_T_V_S_H_I_N_E",
            category = "Adventure"
        ),
        Item(
            id = 6,
            title = "Arctic Wonders",
            subtitle = "Ice and snow",
            description = "Witness the northern lights and explore icy landscapes. " +
                    "From glacier hiking to dog sledding, discover the Arctic's unique beauty. " +
                    "An expedition to the edge of the world.",
            imageUrl = "https://fastly.picsum.photos/id/15/800/450.jpg?hmac=X-m6LqWzK_M7pW_Lp-oP1f_N_G_T_V_S_H_I_N_E",
            category = "Adventure"
        ),
        Item(
            id = 7,
            title = "Tropical Island",
            subtitle = "Paradise found",
            description = "Relax on pristine beaches with crystal clear waters. " +
                    "Snorkel in vibrant coral gardens and enjoy fresh tropical fruits. " +
                    "The ultimate beach getaway for relaxation and rejuvenation.",
            imageUrl = "https://fastly.picsum.photos/id/16/800/450.jpg?hmac=X-m6LqWzK_M7pW_Lp-oP1f_N_G_T_V_S_H_I_N_E",
            category = "Beach"
        ),
        Item(
            id = 8,
            title = "Historic Journey",
            subtitle = "Time travel",
            description = "Walk through ancient ruins and historic landmarks. " +
            "Learn about civilizations that shaped our world. " +
            "A fascinating journey through time and history.",
            imageUrl = "https://fastly.picsum.photos/id/17/800/450.jpg?hmac=X-m6LqWzK_M7pW_Lp-oP1f_N_G_T_V_S_H_I_N_E",
            category = "Culture"
        ),
        Item(
            id = 9,
            title = "Wildlife Safari",
            subtitle = "Big five",
            description = "Spot the big five in their natural habitat. " +
            "Our experienced rangers ensure safe and respectful wildlife encounters. " +
            "An authentic African safari experience.",
            imageUrl = "https://fastly.picsum.photos/id/18/800/450.jpg?hmac=X-m6LqWzK_M7pW_Lp-oP1f_N_G_T_V_S_H_I_N_E",
            category = "Wildlife"
        ),
        Item(
            id = 10,
            title = "Northern Lights",
            subtitle = "Aurora borealis",
            description = "Chase the magical aurora borealis across the Arctic sky. " +
            "Stay in cozy glass igloos and watch nature's light show. " +
            "A bucket-list experience you'll never forget.",
            imageUrl = "https://fastly.picsum.photos/id/19/800/450.jpg?hmac=X-m6LqWzK_M7pW_Lp-oP1f_N_G_T_V_S_H_I_N_E",
            category = "Adventure"
        )
    )

    fun getItemById(id: Int): Item? = items.find { it.id == id }
}