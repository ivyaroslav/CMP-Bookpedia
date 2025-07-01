package com.plcoding.bookpedia.book.data.dto.network

import com.plcoding.bookpedia.book.data.dto.SearchResponseDto
import io.ktor.client.*
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.core.data.safeCall
import com.plcoding.bookpedia.core.domain.Result
import io.ktor.client.request.*
import kotlin.collections.get

private const val BASE_URL = "https://openlibrary.org"
class KtorRemoteBookDataSource(
    private val httpClient: HttpClient
): RemoteBookDataSource {
    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): Result<SearchResponseDto, DataError.Remote> {
        return safeCall <SearchResponseDto> {
            httpClient.get(
                urlString = "$BASE_URL/search.json"
            ) {
                parameter("q", query)
                parameter("limit", resultLimit)
                parameter("language", "eng")
                parameter(
                    "fields",
                    "key,title,author_name,author_key,cover_edition_key,cover_i,ratings_average,ratings_count,first_publish_year,language,number_of_pages_median,edition_count"
                )
            }
        }
    }
}