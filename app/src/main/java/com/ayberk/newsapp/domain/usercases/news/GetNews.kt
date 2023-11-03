package com.ayberk.newsapp.domain.usercases.news

import androidx.paging.PagingData
import com.ayberk.newsapp.domain.model.Article
import com.ayberk.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources : List<String>) : Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}