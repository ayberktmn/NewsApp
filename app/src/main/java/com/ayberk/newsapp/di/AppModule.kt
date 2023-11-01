package com.ayberk.newsapp.di

import android.app.Application
import com.ayberk.newsapp.data.manger.LocalUserMangerImpl
import com.ayberk.newsapp.domain.manger.LocalUserManger
import com.ayberk.newsapp.domain.usercases.AppEntryUseCases
import com.ayberk.newsapp.domain.usercases.ReadAppEntry
import com.ayberk.newsapp.domain.usercases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}