package com.ayberk.newsapp.domain.usercases.news

import com.ayberk.newsapp.data.local.NewsDao
import com.ayberk.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles   (
    private val newsDao: NewsDao
) {

     operator fun invoke() : Flow<List<Article>> {
       return newsDao.getArticles()
    }
}