package org.isaaccode.calculategame.persistence

interface PersistenceAccessor {

    fun get(key: String): Long
    fun set(key: String, value: Long)


}