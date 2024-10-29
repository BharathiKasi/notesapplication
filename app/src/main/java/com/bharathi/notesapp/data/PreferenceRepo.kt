package com.bharathi.notesapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

val Context.dataStore: DataStore<Preferences> by  preferencesDataStore("settings")
class PreferenceRepo private constructor(val context: Context) {

    private val dataStore = context.dataStore
    companion object {
        @Volatile
        private var INSTANCE: PreferenceRepo? = null

        fun getInstance(context: Context): PreferenceRepo {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return it
                }
                val instance = PreferenceRepo(context)
                INSTANCE = instance
                return instance
            }
        }
    }

    suspend fun saveEmail(email:String){
        dataStore.edit{
            it[PreferenceConst.email] = email
        }
    }

    suspend fun updateIsLoggedIn(isLoggedIn:Boolean){
        dataStore.edit {
            it[PreferenceConst.isLoggedIn] = isLoggedIn
        }
    }

    suspend fun setUserName(userName:String){
        dataStore.edit {
            it[PreferenceConst.username] = userName
        }
    }

    val  isLoggedIn = dataStore.data.map {
        it[PreferenceConst.isLoggedIn]?: false
    }

    val  loggedInEmail = dataStore.data.map {
        it[PreferenceConst.email]?: ""
    }

    val userName = dataStore.data.map {
        it[PreferenceConst.username]
    }


    private object PreferenceConst {
        val isLoggedIn = booleanPreferencesKey("isLoggedIn")
        val email = stringPreferencesKey("email")
        val username = stringPreferencesKey("userName")
    }

}