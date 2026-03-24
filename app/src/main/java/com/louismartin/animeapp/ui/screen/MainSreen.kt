package com.louismartin.animeapp.ui.screen

import androidx.compose.foundation.background

import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp

import androidx.compose.ui.text.input.TextFieldValue

import com.louismartin.animeapp.data.AnimeEntity

import com.louismartin.animeapp.viewmodel.AnimeViewModel

@Composable

fun MainScreen(

    isDarkMode: Boolean,

    onThemeChange: (Boolean) -> Unit,

    viewModel: AnimeViewModel

) {

    var showOnlyWatched by remember { mutableStateOf(false) }

    val descriptionVisibility = remember { mutableStateMapOf<Int, Boolean>() }

    // Formulaire pour ajouter un nouvel anime

    var newTitle by remember { mutableStateOf(TextFieldValue("")) }

    var newDescription by remember { mutableStateOf(TextFieldValue("")) }

    val animeList by if (showOnlyWatched) {

        viewModel.watchedAnimes.collectAsState()

    } else {

        viewModel.animes.collectAsState()

    }

    Column(

        modifier = Modifier

            .fillMaxSize()

            .background(MaterialTheme.colorScheme.background)

            .padding(16.dp)

    ) {

        // Dark mode switch

        Row(

            horizontalArrangement = Arrangement.SpaceBetween,

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Mode sombre", color = MaterialTheme.colorScheme.onBackground)

            Switch(

                checked = isDarkMode,

                onCheckedChange = { enabled -> onThemeChange(enabled) }

            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        // Bouton filtre watched

        Button(onClick = { showOnlyWatched = !showOnlyWatched }) {

            Text(if (showOnlyWatched) "Tout afficher" else "Voir regardés")

        }

        Spacer(modifier = Modifier.height(16.dp))

        // Formulaire création d'un nouvel anime

        OutlinedTextField(

            value = newTitle,

            onValueChange = { newTitle = it },

            label = { Text("Titre de l'anime") },

            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(

            value = newDescription,

            onValueChange = { newDescription = it },

            label = { Text("Description") },

            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(

            onClick = {

                if (newTitle.text.isNotBlank() && newDescription.text.isNotBlank()) {

                    val anime = AnimeEntity(

                        title = newTitle.text,

                        description = newDescription.text,

                        color = 0xFFAAAAAA, // couleur par défaut

                        isWatched = false

                    )

                    viewModel.insert(anime)

                    newTitle = TextFieldValue("")

                    newDescription = TextFieldValue("")

                }

            }

        ) {

            Text("Ajouter anime")

        }

        Spacer(modifier = Modifier.height(16.dp))

        // Liste des animes

        LazyColumn {

            items(animeList) { anime ->

                val isVisible = descriptionVisibility[anime.id] ?: false

                Column(

                    modifier = Modifier

                        .fillMaxWidth()

                        .padding(8.dp)

                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.1f))

                ) {

                    Text(

                        text = anime.title,

                        modifier = Modifier.clickable {

                            descriptionVisibility[anime.id] = !isVisible

                        },

                        style = MaterialTheme.typography.titleMedium

                    )

                    if (isVisible) {

                        Text(text = anime.description)

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                            Button(onClick = {

                                viewModel.update(anime.copy(isWatched = !anime.isWatched))

                            }) {

                                Text(if (anime.isWatched) "Marquer non vu" else "Marquer vu")

                            }

                            Button(onClick = { viewModel.delete(anime) }) {

                                Text("Supprimer")

                            }

                        }

                    }

                }

            }

        }

    }

}
