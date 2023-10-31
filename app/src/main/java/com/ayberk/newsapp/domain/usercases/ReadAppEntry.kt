package com.ayberk.newsapp.domain.usercases

import com.ayberk.newsapp.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(

    private val localUserManger: LocalUserManger
) {

    suspend operator fun invoke():Flow<Boolean>{
       return localUserManger.readAppEntry()
    }
}