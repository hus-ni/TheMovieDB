package com.muhammadhusniabdillah.themoviedb.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val EMAIL_KEY = stringPreferencesKey("email")

class SessionPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun deleteSession() {
        dataStore.edit {
            it[EMAIL_KEY] = ""
        }
    }

    fun getSession(): Flow<String> = dataStore.data.map {
        it[EMAIL_KEY] ?: ""
    }

    suspend fun saveSession(email: String) {
        dataStore.edit {
            it[EMAIL_KEY] = email
        }
    }
}