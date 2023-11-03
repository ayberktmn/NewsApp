package com.ayberk.newsapp.domain.usercases.app_entry

import com.ayberk.newsapp.domain.manger.LocalUserManger

class SaveAppEntry(
    private val localUserManger: LocalUserManger
) {

    suspend operator fun invoke (){
        localUserManger.saveAppEntry()
    }
}