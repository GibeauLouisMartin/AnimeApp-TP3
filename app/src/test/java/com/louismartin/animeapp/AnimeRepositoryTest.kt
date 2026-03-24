package com.louismartin.animeapp

import com.louismartin.animeapp.data.AnimeDao
import com.louismartin.animeapp.data.AnimeEntity
import com.louismartin.animeapp.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FakeAnimeDao : AnimeDao {
    private val _animes = MutableStateFlow<List<AnimeEntity>>(emptyList())

    override fun getAll(): Flow<List<AnimeEntity>> = _animes

    override fun getWatched(): Flow<List<AnimeEntity>> =
        _animes.map { list ->
            list.filter { it.isWatched }
        }

    override suspend fun insert(anime: AnimeEntity) {
        _animes.value = _animes.value + anime
    }

    override suspend fun update(anime: AnimeEntity) {
        _animes.value = _animes.value.map {
            if (it.id == anime.id) anime else it
        }
    }

    override suspend fun delete(anime: AnimeEntity) {
        _animes.value = _animes.value.filter { it.id != anime.id }
    }
}

class AnimeRepositoryTest {

    private lateinit var fakeDao: FakeAnimeDao
    private lateinit var repository: AnimeRepository

    @Before
    fun setup() {
        fakeDao = FakeAnimeDao()
        repository = AnimeRepository(fakeDao)
    }

    @Test
    fun insert_anime_addsToList() = runBlocking {
        val anime = AnimeEntity(
            id = 1,
            title = "Test",
            description = "Desc",
            color = 0xFFAAAAAAL,
            isWatched = false
        )

        repository.insert(anime)

        val all = repository.allAnimes.first()

        assertEquals(1, all.size)
    }

    @Test
    fun update_anime_modifiesEntry() = runBlocking {
        val anime = AnimeEntity(
            id = 1,
            title = "Test",
            description = "Desc",
            color = 0xFFAAAAAAL,
            isWatched = false
        )

        repository.insert(anime)

        val updatedAnime = anime.copy(isWatched = true)
        repository.update(updatedAnime)

        val all = repository.allAnimes.first()

        assertEquals(true, all[0].isWatched)
    }

    @Test
    fun delete_anime_removesEntry() = runBlocking {
        val anime = AnimeEntity(
            id = 1,
            title = "Test",
            description = "Desc",
            color = 0xFFAAAAAAL,
            isWatched = false
        )

        repository.insert(anime)
        repository.delete(anime)

        val all = repository.allAnimes.first()

        assertEquals(0, all.size)
    }
}