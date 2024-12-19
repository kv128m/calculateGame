package org.isaaccode.calculategame

import org.isaaccode.calculategame.persistence.PersistenceAccessor
import org.isaaccode.calculategame.resources.ResourceLoader
import org.isaaccode.calculategame.time.DateTimeProvider
import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun getPersistenceAccessor(): PersistenceAccessor {
    return IOSPersistenceAccessor()
}

actual fun getDateTimeProvider() : DateTimeProvider {
    return IOSDateTimeProvider()
}

actual fun getResourceLoader(): ResourceLoader {
    return IOSResourceLoader()
}
