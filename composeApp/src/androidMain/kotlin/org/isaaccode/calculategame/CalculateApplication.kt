package org.isaaccode.calculategame

import android.app.Application
import android.content.Context

class CalculateApplication : Application() {
    companion object {
        lateinit var appContext: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}
