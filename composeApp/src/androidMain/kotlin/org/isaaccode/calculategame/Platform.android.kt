package org.isaaccode.calculategame

import android.os.Build
import org.isaaccode.calculategame.persistence.PersistenceAccessor
import org.isaaccode.calculategame.resources.ResourceLoader
import org.isaaccode.calculategame.time.DateTimeProvider

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getPersistenceAccessor(): PersistenceAccessor {
    return AndroidPersistenceAccessor(Racunaljka.appContext.applicationContext)
}

actual fun getDateTimeProvider(): DateTimeProvider {
    return AndroidDateTimeProvider()
}

actual fun getResourceLoader(): ResourceLoader {
    return AndroidResourceLoader(Racunaljka.appContext.applicationContext)
}


