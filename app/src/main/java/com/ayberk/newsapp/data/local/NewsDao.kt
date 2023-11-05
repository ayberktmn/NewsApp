package com.ayberk.newsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ayberk.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun upsert(article: Article)

    @Delete
    abstract suspend fun delete(article: Article)

    @Query("SELECT * FROM Article")
    abstract fun getArticles(): Flow<List<Article>>

}