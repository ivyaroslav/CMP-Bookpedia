package com.plcoding.bookpedia.di

import com.plcoding.bookpedia.book.data.dto.database.DataBaseFactory
import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*
import org.koin.core.module.Module

import org.koin.dsl.module
actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> {
            Darwin.create() {}
        }

    }