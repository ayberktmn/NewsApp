package com.ayberk.newsapp.domain.usercases.news

import com.ayberk.newsapp.data.local.NewsDao
import com.ayberk.newsapp.domain.model.Article
import com.ayberk.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles   (
    private val newsRepository: NewsRepository
) {

     operator fun invoke() : Flow<List<Article>> {
       return newsRepository.selectArticles()
    }
}