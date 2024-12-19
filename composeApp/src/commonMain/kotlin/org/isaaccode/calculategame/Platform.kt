package org.isaaccode.calculategame

import org.isaaccode.calculategame.persistence.PersistenceAccessor
import org.isaaccode.calculategame.resources.ResourceLoader
import org.isaaccode.calculategame.time.DateTimeProvider

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getPersistenceAccessor(): PersistenceAccessor

expect fun getDateTimeProvider() : DateTimeProvider

expect fun getResourceLoader(): ResourceLoader