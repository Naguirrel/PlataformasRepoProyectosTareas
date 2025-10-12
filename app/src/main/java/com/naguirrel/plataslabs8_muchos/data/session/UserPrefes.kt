package com.naguirrel.plataslabs8_muchos.data.session

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import androidx.datastore.preferences.preferencesDataStore

private const val DS_NAME = "user_prefs"
val Context.dataStore by preferencesDataStore(DS_NAME)

object UserPrefsKeys {
    val NAME = stringPreferencesKey("name")
}

class UserPrefs(private val context: Context) {

    val nameFlow: Flow<String?> =
        context.dataStore.data.map { it[UserPrefsKeys.NAME] }

    suspend fun setName(name: String) {
        context.dataStore.edit { it[UserPrefsKeys.NAME] = name }
    }

    suspend fun clearName() {
        context.dataStore.edit { it.remove(UserPrefsKeys.NAME) }
    }
}
