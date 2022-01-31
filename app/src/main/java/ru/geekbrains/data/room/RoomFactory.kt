package ru.geekbrains.data.room

import androidx.room.Room
import ru.geekbrains.App


object RoomFactory {

    private val database: DBStorage by lazy {
        Room.databaseBuilder(App.ContextHolder.context, DBStorage::class.java, "github.db")
            .build()
    }

    fun create(): DBStorage = database
}