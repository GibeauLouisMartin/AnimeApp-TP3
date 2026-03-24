package com.louismartin.animeapp

import android.os.Bundle

import androidx.activity.ComponentActivity

import androidx.activity.compose.setContent

import androidx.compose.runtime.*

import androidx.lifecycle.lifecycleScope

import androidx.room.Room

import com.louismartin.animeapp.data.AppDatabase

import com.louismartin.animeapp.data.DataStoreHelper

import com.louismartin.animeapp.repository.AnimeRepository

import com.louismartin.animeapp.ui.screen.MainScreen

import com.louismartin.animeapp.ui.theme.AnimeAppTheme

import com.louismartin.animeapp.viewmodel.AnimeViewModel

import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: AnimeViewModel

    private lateinit var dataStoreHelper: DataStoreHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(

            applicationContext,

            AppDatabase::class.java,

            "anime_db"

        ).build()

        val repository = AnimeRepository(db.animeDao())

        viewModel = AnimeViewModel(repository)

        dataStoreHelper = DataStoreHelper(this)

        setContent {

            var isDarkMode by remember { mutableStateOf(false) }

            // Observing DataStore

            LaunchedEffect(Unit) {

                dataStoreHelper.isDarkModeFlow.collect { enabled ->

                    isDarkMode = enabled

                }

            }

            AnimeAppTheme(darkTheme = isDarkMode) {

                MainScreen(

                    isDarkMode = isDarkMode,

                    onThemeChange = { enabled ->

                        isDarkMode = enabled

                        lifecycleScope.launch { dataStoreHelper.setDarkMode(enabled) }

                    },

                    viewModel = viewModel

                )

            }

        }

    }

}
