package com.plcoding.bookpedia.di

import com.plcoding.bookpedia.book.data.dto.database.DataBaseFactory
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module
actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> {
            OkHttp.create {}
        }

        single {
            DataBaseFactory(androidApplication())
        }
    }