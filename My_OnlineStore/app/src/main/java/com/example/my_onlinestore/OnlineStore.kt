package com.example.my_onlinestore

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OnlineStore: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}