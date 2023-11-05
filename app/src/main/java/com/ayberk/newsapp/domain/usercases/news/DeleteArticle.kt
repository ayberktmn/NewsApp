package com.ayberk.newsapp.domain.usercases.news

import com.ayberk.newsapp.data.local.NewsDao
import com.ayberk.newsapp.domain.model.Article

class DeleteArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article) {
        newsDao.delete(article)
    }
}