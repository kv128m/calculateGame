package org.isaaccode.calculategame

import org.isaaccode.calculategame.time.DateTimeProvider
import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970

class IOSDateTimeProvider : DateTimeProvider {
    override fun now(): Long {
        return (NSDate().timeIntervalSince1970 * 1000).toLong()
    }
}