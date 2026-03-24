package com.louismartin.animeapp.repository

import com.louismartin.animeapp.data.AnimeDao

import com.louismartin.animeapp.data.AnimeEntity

class AnimeRepository(private val dao: AnimeDao) {

    val allAnimes = dao.getAll()

    val watchedAnimes = dao.getWatched()

    suspend fun insert(anime: AnimeEntity) = dao.insert(anime)

    suspend fun update(anime: AnimeEntity) = dao.update(anime)

    suspend fun delete(anime: AnimeEntity) = dao.delete(anime)

}
