package com.example.uas_a16.Application

import AppContainer
import PendapatanContainer
import android.app.Application

    class PencatatanApp : Application() {
        lateinit var container: AppContainer

        override fun onCreate() {
            super.onCreate()
            container = PendapatanContainer()
        }
    }
