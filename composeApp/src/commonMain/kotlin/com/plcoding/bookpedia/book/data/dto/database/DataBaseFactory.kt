package com.plcoding.bookpedia.book.data.dto.database

import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

expect class DataBaseFactory {
    fun create(): RoomDatabase.Builder<FavoriteBookDatabase>
}