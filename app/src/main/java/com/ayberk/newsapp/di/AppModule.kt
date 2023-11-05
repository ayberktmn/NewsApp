package com.ayberk.newsapp.di

import android.app.Application
import androidx.room.Room
import com.ayberk.newsapp.data.local.NewsDao
import com.ayberk.newsapp.data.local.NewsDatabase
import com.ayberk.newsapp.data.local.NewsTypeConvertor
import com.ayberk.newsapp.data.manger.LocalUserMangerImpl
import com.ayberk.newsapp.data.remote.dto.NewsApi
import com.ayberk.newsapp.data.repository.NewsRepositoryImpl
import com.ayberk.newsapp.domain.manger.LocalUserManger
import com.ayberk.newsapp.domain.repository.NewsRepository
import com.ayberk.newsapp.domain.usercases.app_entry.AppEntryUseCases
import com.ayberk.newsapp.domain.usercases.app_entry.ReadAppEntry
import com.ayberk.newsapp.domain.usercases.app_entry.SaveAppEntry
import com.ayberk.newsapp.domain.usercases.news.DeleteArticle
import com.ayberk.newsapp.domain.usercases.news.GetNews
import com.ayberk.newsapp.domain.usercases.news.NewsUseCases
import com.ayberk.newsapp.domain.usercases.news.SearchNews
import com.ayberk.newsapp.domain.usercases.news.SelectArticles
import com.ayberk.newsapp.domain.usercases.news.UpsertArticle
import com.ayberk.newsapp.util.Constants.BASE_URL
import com.ayberk.newsapp.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerLocalUserManger(
        application: Application
    ): LocalUserManger = LocalUserMangerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

    @Provides
    @Singleton
    fun proiveNewsApi(): NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi :: class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            selectArticles = SelectArticles(newsDao)

        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ) : NewsDao = newsDatabase.newsDao
}