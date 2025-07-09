package com.plcoding.bookpedia.book.data.dto.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DataBaseFactory {
    actual fun create(): RoomDatabase.Builder<FavoriteBookDatabase> {
       val os = System.getProperty("os.name").toLowerCase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "Bookpedia")
            os.contains("mac") -> File(userHome, "Library/Application Support/Bookpedia")
            else -> File(userHome, ".local/share/Bookpedia")
        }

        if(!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, FavoriteBookDatabase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}