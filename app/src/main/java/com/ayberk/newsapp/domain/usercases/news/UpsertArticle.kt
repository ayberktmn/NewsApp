package com.ayberk.newsapp.domain.usercases.news

import com.ayberk.newsapp.data.local.NewsDao
import com.ayberk.newsapp.domain.model.Article
import com.ayberk.newsapp.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository

) {

    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)
    }
}