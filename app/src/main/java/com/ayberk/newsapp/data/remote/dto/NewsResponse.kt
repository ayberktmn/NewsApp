package com.ayberk.newsapp.data.remote.dto

import com.ayberk.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)