package ru.geekbrains.data

import android.util.Log
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.data.room.RoomFactory

class GitHubUserRepositoryImpl: GitHubUserRepository {

    private val gitHubApi = GitHubApiFactory.create()
    private val roomDb = RoomFactory.create().getGitHubUserDao()

    override fun getUsers(): Single<List<GitHubUser>> {
        return roomDb.getUsers()
            .flatMap {
                if (it.isEmpty()) {
                    Log.d("tog", "isEmpty")
                    gitHubApi.fetchUsers()
                        .map { resultFromServer ->
                            roomDb.saveUsers(resultFromServer)
                            resultFromServer
                        }
                } else {
                    Log.d("tug", "isNotEmpty")
                    Single.just(it)
                }
            }
    }


    override fun getUserByLogin(userId: String): Single<GitHubUser> {
        return roomDb.getUserByLogin(userId).flatMap {
            if (it.name==null){
                gitHubApi.fetchUserByLogin(userId).map {
                        resultFromServer ->
                    roomDb.saveUser(resultFromServer)
                    resultFromServer
                }
            } else{
                Single.just(it)
            }
        }
    }
}