package com.louismartin.animeapp

import android.os.Bundle

import androidx.activity.ComponentActivity

import androidx.activity.compose.setContent

import androidx.compose.runtime.*

import com.louismartin.animeapp.ui.screen.MainScreen

import com.louismartin.animeapp.ui.theme.AnimeAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            var isDarkMode by remember { mutableStateOf(false) }

            AnimeAppTheme(darkTheme = isDarkMode) {

                MainScreen(

                    isDarkMode = isDarkMode,

                    onThemeChange = { isDarkMode = it }

                )

            }

        }

    }

}
