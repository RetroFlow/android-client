package com.example.scame.retroflowmvp.boards.view.sprints

data class RetroSection(val sectionId: String, val name: String, val issues: List<Issue>, val open: Boolean)

data class Issue(val id: String, val title: String, val description: String,
                 val comments: List<Comment>, val assignee: String?, val canEdit: Boolean)

data class Comment(val id: String, val author: String, val text: String)