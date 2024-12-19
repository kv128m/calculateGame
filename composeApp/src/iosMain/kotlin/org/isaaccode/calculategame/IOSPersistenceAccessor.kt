package org.isaaccode.calculategame

import org.isaaccode.calculategame.persistence.PersistenceAccessor

import platform.Foundation.NSUserDefaults

class IOSPersistenceAccessor : PersistenceAccessor {

    private val userDefaults = NSUserDefaults.standardUserDefaults()

    override fun get(key: String): Long {
        return userDefaults.integerForKey(key)
    }

    override fun set(key: String, value: Long) {
        userDefaults.setInteger(value, key)
    }
}
