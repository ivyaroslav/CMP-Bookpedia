package com.plcoding.bookpedia.book.data.dto.network

import io.ktor.client.*
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.core.domain.Result

class KtorRemoteBookDataSource(
    private val httpClient: HttpClient
) {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ):Result<List<Book>, DataError.Remote> {

    }
}