package com.louismartin.animeapp.data

import androidx.compose.ui.graphics.Color

data class Anime(

    val id: Int,

    val title: String,

    val description: String,

    val color: Color

)

val animeList = listOf(

    Anime(1, "Naruto", "Ninja qui veut devenir Hokage", Color(0xFFFFA500)),
    Anime(2, "Attack on Titan", "Combat contre des Titans géants", Color(0xFF8B0000)),
    Anime(3, "Demon Slayer", "Chasseur de démons", Color(0xFF1E90FF)),
    Anime(4, "Jujutsu Kaisen", "Exorcistes modernes", Color(0xFF32CD32)),
    Anime(5, "One Punch Man", "Héros trop puissant", Color(0xFFFF0000)),
    Anime(6, "Tokyo Ghoul", "Humain devenu goule", Color(0xFF4B0082))
)
