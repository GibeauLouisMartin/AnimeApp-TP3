package com.louismartin.animeapp.data

import android.content.Context

import androidx.datastore.preferences.core.booleanPreferencesKey

import androidx.datastore.preferences.core.edit

import androidx.datastore.preferences.preferencesDataStore

import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class DataStoreHelper(private val context: Context) {

    companion object {

        val DARK_MODE = booleanPreferencesKey("dark_mode")

    }

    val isDarkModeFlow: Flow<Boolean> = context.dataStore.data

        .map { preferences -> preferences[DARK_MODE] ?: false }

    suspend fun setDarkMode(enabled: Boolean) {

        context.dataStore.edit { preferences ->

            preferences[DARK_MODE] = enabled

        }

    }

}
