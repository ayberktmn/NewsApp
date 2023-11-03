package com.ayberk.newsapp.domain.usercases.app_entry

import com.ayberk.newsapp.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(

    private val localUserManger: LocalUserManger
) {

     operator fun invoke():Flow<Boolean>{
       return localUserManger.readAppEntry()
    }
}