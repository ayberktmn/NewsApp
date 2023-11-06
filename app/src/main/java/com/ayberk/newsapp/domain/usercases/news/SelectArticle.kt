package com.ayberk.newsapp.domain.usercases.news

import com.ayberk.newsapp.data.local.NewsDao
import com.ayberk.newsapp.domain.model.Article

class SelectArticle (
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(url: String): Article?{
       return newsDao.getArticle(url)
    }
}