package ru.geekbrains.mvpuser

import android.util.Log
import moxy.MvpPresenter
import ru.geekbrains.data.GitHubUserRepository
import ru.geekbrains.navigation.CustomRouter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.mvpuser.UserScreen

class UserPresenter(
    private val userLogin: String,
    private val userRepository: GitHubUserRepository,
    private val router: CustomRouter
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        updateContent()
//        userRepository
//            .getUserByLogin(userLogin)
//            ?.let(viewState::showUser)
    }

    private fun updateContent() {
        userRepository.getUserByLogin(userLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showUser(it)
            },{e-> Log.d("error", e.message.toString())})
    }
}