package com.example.scame.retroflowmvp.boards

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.scame.retroflowmvp.BoardClickEvent
import com.example.scame.retroflowmvp.BottomNavigationActivity
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.boards.addedit.BoardAddEditActivity
import com.example.scame.retroflowmvp.boards.presenter.BoardsPresenter
import com.example.scame.retroflowmvp.boards.view.BoardViewActivity
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.Subscribe



class BoardsFragment: Fragment(), BoardsPresenter.BoardsView {

    companion object {
        fun newInstance() = BoardsFragment()
    }

    @BindView(R.id.progress_bar)
    lateinit var progressBar: ProgressBar

    @BindView(R.id.boards_rv)
    lateinit var boardsRv: RecyclerView

    @BindView(R.id.swipe_refresh_layout)
    lateinit var swipeToRefresh: SwipeRefreshLayout

    @Inject
    lateinit var presenter: BoardsPresenter<BoardsPresenter.BoardsView>

    private lateinit var boardsAdapter: BoardsRvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.boards_fragment, container, false)
        ButterKnife.bind(this, view)

        setupInjection()
        setupBoardsRv()

        setupSwipeToRefreshLayout()

        return view
    }

    private fun setupSwipeToRefreshLayout() {
        swipeToRefresh.setOnRefreshListener {
            presenter.requestBoards()
            swipeToRefresh.isRefreshing = false
        }
    }

    private fun setupBoardsRv() {
        boardsAdapter = BoardsRvAdapter(mutableListOf(), context!!)
        boardsRv.adapter = boardsAdapter
        boardsRv.layoutManager = LinearLayoutManager(context)
    }

    private fun setupInjection() {
        (activity as BottomNavigationActivity).boardsComponent.inject(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.subscribe(this)
        presenter.requestBoards()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
        EventBus.getDefault().unregister(this)
    }

    @OnClick(R.id.board_add_btn)
    fun onBoardAddClick() {
        presenter.addBoardClick()
    }

    override fun onAddBoard() {
        startActivity(BoardAddEditActivity.getIntent(context!!))
    }

    override fun onProgressChange(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onBoards(boards: List<BoardRawModel>) {
        boardsAdapter.rebind(boards)
    }

    override fun onError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Subscribe()
    fun onMessageEvent(event: BoardClickEvent) {
        presenter.openBoard(event.boardModel)
    }

    override fun onOpenBoard(board: BoardRawModel) {
        startActivity(BoardViewActivity.getIntent(board.id, context!!))
    }
}