package com.example.scame.retroflowmvp.injection

import io.reactivex.Scheduler

interface SubscribeOn {

    fun subscribeOn(): Scheduler
}

interface ObserveOn {

    fun observeOn(): Scheduler
}