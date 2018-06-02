package com.example.scame.retroflowmvp.boards.view.sprints

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.RetroFlowApp
import com.example.scame.retroflowmvp.boards.addedit.models.SprintEntity
import com.example.scame.retroflowmvp.boards.view.sprints.adapter.RetroSectionsAdapter
import com.example.scame.retroflowmvp.boards.view.sprints.di.SectionsModule
import com.example.scame.retroflowmvp.boards.view.sprints.presenter.RetroSectionsPresenter
import com.example.scame.retroflowmvp.boards.view.sprints.section.ActionItemsListActivity
import javax.inject.Inject

abstract class SprintBaseFragment: Fragment(), RetroSectionsPresenter.RetroSectionsView {

    companion object {
        val SPRINT_KEY = "sprint_key"
    }

    @BindView(R.id.retro_sections_rv)
    lateinit var retroSectionsRv: RecyclerView

    @Inject
    lateinit var presenter: RetroSectionsPresenter<RetroSectionsPresenter.RetroSectionsView>

    @BindView(R.id.progress_bar)
    lateinit var progressBar: ProgressBar

    private lateinit var sectionsAdapter: RetroSectionsAdapter

    private val sectionsComponent by lazy {
        RetroFlowApp.appComponent.provideRetroSectionsComponents(SectionsModule())
    }

    private val sprintModel by lazy {
        arguments!!.getParcelable<SprintEntity>(SPRINT_KEY)
    }

    abstract val isCurrentSprint: Boolean

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.sprint_fragment, container, false)
        ButterKnife.bind(this, view)

        setupInjection()
        setupSectionsAdapter()

        sectionsAdapter.rebind(sprintModel.columns)

        return view
    }

    private fun setupInjection() {
        sectionsComponent.inject(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.subscribe(this)
        //presenter.requestRetroSections(boardId)
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    private fun setupSectionsAdapter() {
        sectionsAdapter = RetroSectionsAdapter(mutableListOf(), context!!) {
            startActivity(ActionItemsListActivity.getIntent(context!!, it))
        }
        retroSectionsRv.adapter = sectionsAdapter
        retroSectionsRv.layoutManager = LinearLayoutManager(context)
    }

    override fun onRetroSections(retroSections: List<RetroSection>) {
        //sectionsAdapter.rebind(retroSections)
    }

    override fun onProgressChanged(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onError(throwable: Throwable) {
        Log.i("onxError", throwable.toString())
    }
}