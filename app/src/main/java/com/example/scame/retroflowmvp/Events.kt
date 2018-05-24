package com.example.scame.retroflowmvp

import com.example.scame.retroflowmvp.boards.BoardRawModel
import com.example.scame.retroflowmvp.boards.view.sprints.ActionItem
import com.example.scame.retroflowmvp.boards.view.sprints.RetroSection

data class BoardClickEvent(val boardModel: BoardRawModel)

data class SectionClickEvent(val section: RetroSection)

data class ActionItemClickEvent(val actionItem: ActionItem)