package com.ngoctientnt.todoapp.core.extension

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.ngoctientnt.todoapp.data.model.User
import com.ngoctientnt.todoapp.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    private val gson: Gson = Gson()

    suspend fun setUser(value: User?) {
        context.dataStore.edit {
            if (value == null) it.remove(stringPreferencesKey("user"))
            else it[stringPreferencesKey("user")] = gson.toJson(value)
        }
    }

    val getUser: Flow<User?>
        get() = context.dataStore.data.map {
            if (!it.contains(stringPreferencesKey("user"))) return@map null
            else gson.fromJson(it[stringPreferencesKey("user")], User::class.java)
        }

    suspend fun clearDataStore() = context.dataStore.edit { it.clear() }
}