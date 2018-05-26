package com.example.scame.retroflowmvp.boards.view.sprints.section.action_item

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.scame.retroflowmvp.R
import com.example.scame.retroflowmvp.RetroFlowApp
import com.example.scame.retroflowmvp.boards.view.sprints.ActionItem
import com.example.scame.retroflowmvp.boards.view.sprints.Comment
import com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.di.ActionItemModule
import com.example.scame.retroflowmvp.boards.view.sprints.section.action_item.presenter.ActionItemPresenter
import javax.inject.Inject

class ActionItemActivity: AppCompatActivity(), ActionItemPresenter.ActionItemView {

    companion object {

        private const val ACTION_ITEM_KEY = "action_item_key"
        private const val MODEL_KEY = "model_key"

        fun getIntent(context: Context, actionItem: ActionItem): Intent {
            val intent = Intent(context, ActionItemActivity::class.java)
            intent.putExtra(ACTION_ITEM_KEY, actionItem)
            return intent
        }
    }

    @BindView(R.id.action_item_title)
    lateinit var actionItemTitle: TextView

    @BindView(R.id.action_item_description)
    lateinit var actionItemDescription: TextView

    @BindView(R.id.action_item_assignee)
    lateinit var actionItemAssignee: TextView

    @BindView(R.id.comments_rv)
    lateinit var commentsRv: RecyclerView

    @BindView(R.id.comment_input)
    lateinit var commentInput: EditText

    @BindView(R.id.action_item_progress_bar)
    lateinit var actionItemProgressBar: ProgressBar

    @BindView(R.id.comments_progress_bar)
    lateinit var commentsProgressBar: ProgressBar

    @Inject
    lateinit var actionItemPresenter: ActionItemPresenter<ActionItemPresenter.ActionItemView>

    private lateinit var commentsAdapter: ActionItemCommensAdapter

    private val actionItemComponent by lazy {
        RetroFlowApp.appComponent.provideActionItemComponent(ActionItemModule())
    }

    private lateinit var screenModel: ActionItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.action_item_activity_layout)
        ButterKnife.bind(this)

        setScreenModel(savedInstanceState)

        setupCommentsAdapter()

        setupInjection()
    }

    private fun setScreenModel(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            this.screenModel = intent.getParcelableExtra(ACTION_ITEM_KEY)
        } else {
            this.screenModel = savedInstanceState.getParcelable(MODEL_KEY)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(MODEL_KEY, screenModel)
    }

    private fun setupInjection() {
        actionItemComponent.inject(this)
    }

    override fun onStart() {
        super.onStart()
        actionItemPresenter.subscribe(this)
        actionItemPresenter.setModel(screenModel)
        actionItemPresenter.requestComments(screenModel.id)
    }

    override fun onStop() {
        super.onStop()
        actionItemPresenter.unsubscribe()
    }

    override fun onRenderActionItem(actionItem: ActionItem) {
        actionItemTitle.text = actionItem.title
        actionItemDescription.text = actionItem.description
        actionItemAssignee.text = actionItem.assignee
    }

    private fun setupCommentsAdapter() {
        this.commentsAdapter = ActionItemCommensAdapter(screenModel.comments.toMutableList(), this)
        commentsRv.adapter = commentsAdapter
        commentsRv.layoutManager = LinearLayoutManager(this)
    }

    @OnClick(R.id.comment_send_btn)
    fun onCommentSendClick() {
        val commentText = commentInput.text.toString()
        actionItemPresenter.addComment(screenModel, commentText)
    }

    override fun onCommentValidationFailed() {
        Toast.makeText(this, "Cannot send empty message", Toast.LENGTH_SHORT).show()
    }

    override fun onActionItemEdit(edited: ActionItem) {
        screenModel = edited
    }

    override fun onActionItemComments(comments: List<Comment>) {
        commentsAdapter.rebind(comments)
    }

    override fun onError(throwable: Throwable) {
    }

    override fun onActionItemProgressChanged(show: Boolean) {
        actionItemProgressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onCommentsProgressChanged(show: Boolean) {
        commentsProgressBar.visibility = if (show) View.VISIBLE else View.GONE
    }
}