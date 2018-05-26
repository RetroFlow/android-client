package com.example.scame.retroflowmvp.boards.view.sprints.repository

import com.example.scame.retroflowmvp.boards.view.sprints.ActionItem
import com.example.scame.retroflowmvp.boards.view.sprints.RetroSection
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class RetroItemsRepositoryImpl: RetroItemsRepository {

    override fun getRetroSectionsForBoard(boardId: String): Single<List<RetroSection>> {
        val issue1 = ActionItem(1.toString(), "Check backups regularly", "Should be done on daily-basis", listOf(), null, false, 4, true)
        val issue2 = ActionItem(2.toString(), "Stop polluting announcement channel", "Please, use only for real announcements", listOf(), "All", false, 0, false)
        val issue3 = ActionItem(3.toString(), "Clarify scope meetings", "Make sure that everyone know what's going on here", listOf(), "RM", false, 0, true)

        val issue4 = ActionItem(4.toString(), "Start writing rough estimates on tickets", "makes overall picture clearer", listOf(), "developers", true, 3, true)
        val issue5 = ActionItem(5.toString(), "Start using online tools for retrospectives", "process would be simpler and more effective", listOf(), "RM", true, 2, false)
        val issue6 = ActionItem(6.toString(), "Continue running nightly error checkers", "finally it's automated", listOf(), "Ops", false, 1, true)
        val issue7 = ActionItem(7.toString(), "Stop inviting all people that could be possibly interested (any meeting)", "Ineffective and annoying", listOf(), "PM & RM", false, 0, true)

        return Single
                .timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .map { listOf(
                        RetroSection(1.toString(), "Start", listOf(issue1, issue3), true),
                        RetroSection(2.toString(), "Stop", listOf(issue2), true),
                        RetroSection(3.toString(), "Continue", listOf(), true),
                        RetroSection(4.toString(), "Start", listOf(issue4, issue5), false),
                        RetroSection(5.toString(), "Stop", listOf(issue7), false),
                        RetroSection(6.toString(), "Continue", listOf(issue6), false)
                ) }
    }

    override fun createRetroSectionForBoard(retroSection: RetroSection): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun editRetroSectionForBoard(boardId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeRetroSectionForBoard(boardId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}