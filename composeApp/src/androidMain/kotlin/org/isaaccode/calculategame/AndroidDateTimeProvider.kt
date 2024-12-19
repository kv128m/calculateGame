package org.isaaccode.calculategame

import org.isaaccode.calculategame.time.DateTimeProvider

class AndroidDateTimeProvider() : DateTimeProvider {
    override fun now(): Long {
        return System.currentTimeMillis()
    }
}