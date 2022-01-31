package ru.geekbrains.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.geekbrains.data.GitHubUser

@Database(exportSchema = false, entities = [GitHubUser::class], version = 1)
abstract class DBStorage: RoomDatabase() {

    abstract fun getGitHubUserDao(): GitHubUserDao
}