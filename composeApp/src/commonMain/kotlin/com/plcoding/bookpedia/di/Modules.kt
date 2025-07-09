package com.plcoding.bookpedia.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.plcoding.bookpedia.book.data.dto.database.DataBaseFactory
import com.plcoding.bookpedia.book.data.dto.database.FavoriteBookDatabase
import com.plcoding.bookpedia.book.data.dto.network.KtorRemoteBookDataSource
import com.plcoding.bookpedia.book.data.dto.network.RemoteBookDataSource
import com.plcoding.bookpedia.book.data.dto.repository.DefaultBookRepository
import com.plcoding.bookpedia.book.domain.BookRepository
import com.plcoding.bookpedia.core.data.HttpClientFactory

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import com.plcoding.bookpedia.book.presentation.book_list.BookListViewModel
import com.plcoding.bookpedia.book.presentation.book_list.bookDetail.components.BookDetailViewModel
import com.plcoding.bookpedia.book.presentation.book_list.SelectedBookViewModel
import org.koin.core.module.Module

expect val platformModule: Module
val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    single {
        get<DataBaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single{ get<FavoriteBookDatabase>().favoriteBookDao}

    viewModelOf(::BookListViewModel)
    viewModelOf(::SelectedBookViewModel)
    viewModelOf(::BookDetailViewModel)


}