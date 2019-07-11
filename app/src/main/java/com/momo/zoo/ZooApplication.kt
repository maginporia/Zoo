package com.momo.zoo

import android.app.Application
import com.momo.zoo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ZooApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ZooApplication)
            modules(appModule)
        }
    }
}