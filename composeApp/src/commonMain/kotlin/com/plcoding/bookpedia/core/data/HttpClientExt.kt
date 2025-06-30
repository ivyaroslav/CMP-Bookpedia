package com.plcoding.bookpedia.core.data
import io.ktor.client.statement.HttpResponse
import com.plcoding.bookpedia.core.domain.DataError
import io.ktor.client.call.*

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, DataError.Remote> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch(e: NoTransformationFoundException) {
                Result.Error(DataError.Remote.SERIALIZATION)
            }
        }
    }
}