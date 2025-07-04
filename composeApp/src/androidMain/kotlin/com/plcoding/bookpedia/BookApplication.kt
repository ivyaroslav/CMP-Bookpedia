package com.plcoding.bookpedia

import android.app.Application
import com.plcoding.bookpedia.di.initKoin
import io.ktor.http.*
import org.koin.android.ext.koin.androidContext

class BookApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BookApplication)
        }
    }
}