package com.updown.superdo

import android.app.Application
import com.updown.superdo.koin.appModules
import com.updown.superdo.koin.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SuperApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SuperApp)
            modules(listOf(
                appModules,
                viewModelModules
            ))
        }
    }
}