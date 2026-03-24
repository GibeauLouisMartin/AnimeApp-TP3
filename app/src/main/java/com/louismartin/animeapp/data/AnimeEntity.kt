package com.louismartin.animeapp.data
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class AnimeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val color: Long,
    val isWatched: Boolean = false
)