package com.louismartin.animeapp.data

import androidx.room.*

import kotlinx.coroutines.flow.Flow

@Dao

interface AnimeDao {

    @Query("SELECT * FROM AnimeEntity")

    fun getAll(): Flow<List<AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)

    suspend fun insert(anime: AnimeEntity)

    @Update

    suspend fun update(anime: AnimeEntity)

    @Delete

    suspend fun delete(anime: AnimeEntity)

    @Query("SELECT * FROM AnimeEntity WHERE isWatched = 1")

    fun getWatched(): Flow<List<AnimeEntity>>

}
