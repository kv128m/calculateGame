package org.isaaccode.calculategame

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import org.isaaccode.calculategame.resources.ResourceLoader
import platform.Foundation.NSBundle
import platform.Foundation.NSData
import platform.Foundation.NSString
import platform.Foundation.NSURL
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.dataWithContentsOfFile
import platform.Foundation.dataWithContentsOfURL
import platform.Foundation.stringWithUTF8String

class IOSResourceLoader : ResourceLoader {
    override fun loadJSON(path: String): String {
        val path = NSBundle.mainBundle.pathForResource(path, null)
            ?: throw IllegalArgumentException("Resource not found: $path")
        val url = NSURL.fileURLWithPath(path!!)
        val data = NSData.dataWithContentsOfURL(url)
        //return NSString.create(data, encoding = NSUTF8StringEncoding).toString()
        return ""
    }

    @OptIn(ExperimentalForeignApi::class)
    override fun loadResourceAsString(resourcePath: String): String {
        val fullPath = NSBundle.mainBundle.pathForResource(resourcePath, null)
            ?: throw IllegalArgumentException("Resource not found: $resourcePath")
        val data = NSData.dataWithContentsOfFile(fullPath)
            ?: throw IllegalArgumentException("Failed to load data from $resourcePath")
        return NSString.stringWithUTF8String(data.bytes as CPointer<ByteVar>?)?.toString()
            ?: throw IllegalArgumentException("Failed to decode data as UTF-8")
    }

}