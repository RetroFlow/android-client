package com.example.scame.retroflowmvp.boards

import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.example.scame.retroflowmvp.boards.addedit.models.BoardCreateBody
import com.example.scame.retroflowmvp.boards.addedit.models.BoardDefaultSettings
import com.example.scame.retroflowmvp.injection.ObserveOn
import com.example.scame.retroflowmvp.injection.SubscribeOn
import com.example.scame.retroflowmvp.networking.ApiInterface
import com.example.scame.retroflowmvp.utils.getToken
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class BoardsRepositoryImpl(private val apiInterface: ApiInterface,
                           private val sp: SharedPreferences,
                           private val subscribeOn: SubscribeOn,
                           private val observeOn: ObserveOn): BoardsRepository {

    override fun getDefaultBoardSettings(): Single<BoardDefaultSettings> {
        return apiInterface
                .getBoardDefaultSettings("Bearer " + requireNotNull(sp.getToken()))
                .subscribeOn(subscribeOn.subscribeOn())
                .observeOn(observeOn.observeOn())
    }

    override fun createBoard(name: String, settings: BoardDefaultSettings): Single<BoardApiModel> {
        return apiInterface.createBoard("Bearer " + requireNotNull(sp.getToken()), BoardCreateBody(name, settings))
                .subscribeOn(subscribeOn.subscribeOn())
                .observeOn(observeOn.observeOn())
    }

    override fun editBoard(board: BoardRawModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBoards(): Single<List<BoardApiModel>> {
        return apiInterface.getBoards("Bearer " + requireNotNull(sp.getToken()))
                .subscribeOn(subscribeOn.subscribeOn())
                .observeOn(observeOn.observeOn())

    }

    override fun removeBoard(id: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}