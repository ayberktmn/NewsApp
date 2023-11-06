package com.ayberk.newsapp.presentation.details.components

import com.ayberk.newsapp.domain.model.Article

sealed class DetailsEvent {

  data class UpsertDeleteArticle(val article : Article) : DetailsEvent()

  object RemoveSideEffect : DetailsEvent()
}