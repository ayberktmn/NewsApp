package com.ayberk.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ayberk.newsapp.domain.model.Article

@Database(entities = [Article::class], version = 2)
@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract val newsDao : NewsDao
}