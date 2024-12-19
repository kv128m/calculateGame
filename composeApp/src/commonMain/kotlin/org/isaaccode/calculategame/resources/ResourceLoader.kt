package org.isaaccode.calculategame.resources

interface ResourceLoader {
    fun loadJSON(path: String): String
    fun loadResourceAsString(resourcePath: String): String
}