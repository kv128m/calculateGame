package org.isaaccode.calculategame.components

import kotlinx.serialization.json.Json
import org.isaaccode.calculategame.getResourceLoader

class LocalizedStrings(private val locale: String) {
    private val strings: Map<String, String>

    init {
        val fileName = "$locale.json"
        val fileContent = getResourceLoader().loadJSON(fileName) // Implement file loader for your platform
        strings = Json.decodeFromString(fileContent)
    }

    fun get(key: String): String {
        return strings[key] ?: "[$key]"
    }
}
