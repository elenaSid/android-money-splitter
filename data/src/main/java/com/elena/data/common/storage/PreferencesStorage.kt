package com.elena.data.common.storage

import android.content.Context
import com.elena.domain.common.KeyValueStorage

private const val APP_PREFERENCES: String = "app_preferences"

/**
 * @author elena
 */
class PreferencesStorage(appContext: Context) : KeyValueStorage {

    private var sharedPreferences =
            appContext.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    override fun has(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    override fun remove(key: String) {
        if (sharedPreferences.contains(key)) {
            sharedPreferences.edit().remove(key).apply()
        }
    }

    override fun getValue(key: String, defaultValue: String?): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    override fun setValue(key: String, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getValue(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    override fun setValue(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun getValue(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    override fun setValue(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun getValue(key: String, defaultValue: Long): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    override fun setValue(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }
}