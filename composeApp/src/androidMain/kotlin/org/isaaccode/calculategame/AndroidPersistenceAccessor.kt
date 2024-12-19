package org.isaaccode.calculategame

import android.content.Context
import android.content.SharedPreferences
import org.isaaccode.calculategame.persistence.PersistenceAccessor

class AndroidPersistenceAccessor(private val context: Context) : PersistenceAccessor {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

    override fun get(key: String): Long {
        return sharedPreferences.getLong(key, 0)
    }

    override fun set(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }
}
