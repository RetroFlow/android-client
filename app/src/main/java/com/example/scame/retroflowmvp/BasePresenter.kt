package com.example.scame.retroflowmvp

interface BasePresenter<T> {

    fun subscribe(view: T)

    fun unsubscribe()
}