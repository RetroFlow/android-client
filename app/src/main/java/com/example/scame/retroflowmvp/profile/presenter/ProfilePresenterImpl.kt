package com.example.scame.retroflowmvp.profile.presenter

import com.example.scame.retroflowmvp.entry_point.EntryRepository
import com.example.scame.retroflowmvp.profile.profile.ProfileRepository
import io.reactivex.disposables.CompositeDisposable

class ProfilePresenterImpl<T: ProfilePresenter.ProfileView>(
        private val entryRepository: EntryRepository,
        private val profileRepository: ProfileRepository
): ProfilePresenter<T> {

    private var view: T? = null

    private val composite = CompositeDisposable()

    override fun requestProfileInfo() {
        composite.add(profileRepository
                .requestProfile()
                .doOnSubscribe { view?.onProgressChanged(true) }
                .doFinally { view?.onProgressChanged(false) }
                .subscribe(
                        { view?.onProfile(it) },
                        { view?.onError(it) }
                )
        )
    }

    override fun requestLogout() {
        composite.add(entryRepository
                .logout()
                .doOnSubscribe { view?.onProgressChanged(true) }
                .doFinally { view?.onProgressChanged(false) }
                .subscribe(
                        { view?.onLogout() },
                        { view?.onError(it) }
                )
        )
    }

    override fun subscribe(view: T) {
        this.view = view
    }

    override fun unsubscribe() {
        this.view = null
        composite.clear()
    }
}