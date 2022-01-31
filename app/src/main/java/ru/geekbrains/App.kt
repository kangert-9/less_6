package ru.geekbrains

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import ru.geekbrains.navigation.CustomRouter

class App: Application() {

    @SuppressLint("StaticFieldLeak")
    object ContextHolder { lateinit var context: Context }

    override fun onCreate() {
        super.onCreate()
        ContextHolder.context = this
    }

    companion object Navigation {

        private val cicerone: Cicerone<CustomRouter> by lazy {
            Cicerone.create(CustomRouter())
        }
        val navigatorHolder = cicerone.getNavigatorHolder()
        val router = cicerone.router
    }
}