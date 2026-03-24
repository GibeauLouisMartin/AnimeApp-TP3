package com.louismartin.animeapp.viewmodel

import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope

import com.louismartin.animeapp.data.AnimeEntity

import com.louismartin.animeapp.repository.AnimeRepository

import kotlinx.coroutines.flow.SharingStarted

import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.flow.stateIn

import kotlinx.coroutines.launch

class AnimeViewModel(private val repository: AnimeRepository) : ViewModel() {

    val animes: StateFlow<List<AnimeEntity>> =

        repository.allAnimes.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val watchedAnimes: StateFlow<List<AnimeEntity>> =

        repository.watchedAnimes.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insert(anime: AnimeEntity) = viewModelScope.launch { repository.insert(anime) }

    fun update(anime: AnimeEntity) = viewModelScope.launch { repository.update(anime) }

    fun delete(anime: AnimeEntity) = viewModelScope.launch { repository.delete(anime) }

}
