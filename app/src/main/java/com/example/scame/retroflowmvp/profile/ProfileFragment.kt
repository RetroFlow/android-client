package com.example.scame.retroflowmvp.profile

import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.scame.retroflowmvp.BottomNavigationActivity
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.entry_point.login.LoginActivity
import com.example.scame.retroflowmvp.profile.presenter.ProfilePresenter
import javax.inject.Inject

class ProfileFragment: Fragment(), ProfilePresenter.ProfileView {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    @Inject
    lateinit var profilePresenter: ProfilePresenter<ProfilePresenter.ProfileView>

    @BindView(R.id.progress_bar)
    lateinit var progressBar: ProgressBar

    private val profileComponent by lazy {
        (activity as BottomNavigationActivity).profileComponent
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.profile_fragment, container, false)
        ButterKnife.bind(this, view)

        setupInjection()

        return view
    }

    private fun setupInjection() {
        profileComponent.inject(this)
    }

    override fun onProfile(profile: ProfileModel) {

    }

    override fun onLogout() {
        startActivity(LoginActivity.getIntent(requireNotNull(context)))
        activity?.finishAffinity()
    }

    override fun onError(throwable: Throwable) {
    }

    override fun onProgressChanged(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onStart() {
        super.onStart()
        profilePresenter.subscribe(this)
        profilePresenter
    }

    override fun onStop() {
        super.onStop()
        profilePresenter.unsubscribe()
    }

    @OnClick(R.id.logout_btn)
    fun onLogoutClick() {
        profilePresenter.requestLogout()
    }
}