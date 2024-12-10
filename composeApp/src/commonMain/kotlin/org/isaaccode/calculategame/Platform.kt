package org.isaaccode.calculategame

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform