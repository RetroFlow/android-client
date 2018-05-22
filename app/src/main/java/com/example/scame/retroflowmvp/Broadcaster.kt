package com.example.scame.retroflowmvp

import org.greenrobot.eventbus.EventBus

class Broadcaster {

    companion object {

        fun <T> post(msg: T) {
            EventBus.getDefault().post(msg)
        }

        fun <T> postPersistent(msg: T) {
            EventBus.getDefault().postSticky(msg)
        }
    }
}