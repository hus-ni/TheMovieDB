package com.muhammadhusniabdillah.themoviedb.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val EMAIL_KEY = stringPreferencesKey("email_key")
private val NAME_KEY = stringPreferencesKey("name_key")


class SessionPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun deleteSession() {
        dataStore.edit {
            it[EMAIL_KEY] = ""
            it[NAME_KEY] = ""
        }
    }

    fun getSession(): Flow<SessionData> = dataStore.data.map {
        SessionData(
            it[EMAIL_KEY] ?: "",
            it[NAME_KEY] ?: ""
        )
    }

    suspend fun saveSession(email: String, name: String) {
        dataStore.edit {
            it[EMAIL_KEY] = email
            it[NAME_KEY] = name
        }
    }
}

data class SessionData(
    val email: String,
    val name: String
)