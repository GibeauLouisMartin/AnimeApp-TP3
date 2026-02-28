package com.louismartin.animeapp.ui.screen

import androidx.compose.foundation.background

import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp

import com.louismartin.animeapp.data.animeList

@Composable

fun MainScreen(

    isDarkMode: Boolean,

    onThemeChange: (Boolean) -> Unit

) {

    var animesVus by rememberSaveable { mutableStateOf(0) }

    val descriptionVisibility = remember { mutableStateMapOf<Int, Boolean>() }

    Column(

        modifier = Modifier

            .fillMaxSize()

            .background(MaterialTheme.colorScheme.background)

            .padding(16.dp)

    ) {

        Text(

            text = "Anime App",

            style = MaterialTheme.typography.headlineMedium,

            color = MaterialTheme.colorScheme.onBackground

        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(

            horizontalArrangement = Arrangement.SpaceBetween,

            modifier = Modifier.fillMaxWidth()

        ) {

            Text(

                "Mode sombre",

                color = MaterialTheme.colorScheme.onBackground

            )

            Switch(

                checked = isDarkMode,

                onCheckedChange = { onThemeChange(it) }

            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(

            horizontalArrangement = Arrangement.SpaceEvenly,

            modifier = Modifier.fillMaxWidth()

        ) {

            Button(onClick = { if (animesVus > 0) animesVus-- }) { Text("Moins") }

            Button(onClick = { if (animesVus < animeList.size) animesVus++ }) { Text("Regardé +1") }

        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(

            "Nombre d'animes regardés : $animesVus / ${animeList.size}",

            color = MaterialTheme.colorScheme.onBackground

        )

        if (animesVus == animeList.size) {

            Text(

                "🎉 Bravo ! Tu as regardé tous les animes !",

                color = MaterialTheme.colorScheme.primary

            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(animeList) { anime ->

                val isVisible = descriptionVisibility[anime.id] ?: false

                Card(

                    modifier = Modifier

                        .fillMaxWidth()

                        .padding(8.dp),

                    colors = CardDefaults.cardColors(

                        containerColor = anime.color.copy(alpha = 0.3f)

                    )

                ) {

                    Column(

                        modifier = Modifier

                            .padding(16.dp)

                            .background(MaterialTheme.colorScheme.surface)

                    ) {

                        Text(

                            text = anime.title,

                            style = MaterialTheme.typography.titleMedium,

                            color = anime.color,

                            modifier = Modifier

                                .background(anime.color.copy(alpha = 0.2f))

                                .padding(horizontal = 8.dp, vertical = 4.dp)

                                .clickable {

                                    descriptionVisibility[anime.id] =

                                        !(descriptionVisibility[anime.id] ?: false)

                                }

                        )

                        if (isVisible) {

                            Text(

                                text = anime.description,

                                color = MaterialTheme.colorScheme.onSurface,

                                modifier = Modifier.padding(top = 4.dp)

                            )

                        }

                    }

                }

            }

        }

    }

}
