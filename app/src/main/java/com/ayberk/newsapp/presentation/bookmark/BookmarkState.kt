package com.ayberk.newsapp.presentation.bookmark

import com.ayberk.newsapp.domain.model.Article

data class BookmarkState(
    val articles : List<Article> = emptyList()
)
