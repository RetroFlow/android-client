package com.example.scame.retroflowmvp.boards.view.sprints

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.RetroFlowApp
import com.example.scame.retroflowmvp.SectionClickEvent
import com.example.scame.retroflowmvp.boards.view.sprints.adapter.RetroSectionsAdapter
import com.example.scame.retroflowmvp.boards.view.sprints.di.SectionsModule
import com.example.scame.retroflowmvp.boards.view.sprints.presenter.RetroSectionsPresenter
import com.example.scame.retroflowmvp.boards.view.sprints.section.ActionItemsActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

abstract class SprintBaseFragment: Fragment(), RetroSectionsPresenter.RetroSectionsView {

    companion object {
        val BOARD_ID_KEY = "board_id_key"
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

    private val boardId by lazy {
        arguments?.getString(BOARD_ID_KEY) ?: "0"
    }

    abstract val isCurrentSprint: Boolean

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.sprint_fragment, container, false)
        ButterKnife.bind(this, view)

        setupInjection()
        setupSectionsAdapter()

        return view
    }

    private fun setupInjection() {
        sectionsComponent.inject(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.subscribe(this)
        presenter.requestRetroSections(boardId)
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
        EventBus.getDefault().unregister(this)
    }

    private fun setupSectionsAdapter() {
        sectionsAdapter = RetroSectionsAdapter(mutableListOf(), context!!)
        retroSectionsRv.adapter = sectionsAdapter
        retroSectionsRv.layoutManager = LinearLayoutManager(context)
    }

    override fun onRetroSections(retroSections: List<RetroSection>) {
        sectionsAdapter.rebind(retroSections)
    }

    override fun onProgressChanged(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Subscribe
    fun onRetroSectionClick(sectionClickEvent: SectionClickEvent) {
        startActivity(ActionItemsActivity.getIntent(context!!, sectionClickEvent.section))
    }
}