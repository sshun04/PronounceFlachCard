package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.new_arch.data.RealmDatabaseRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.SharedPrefRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener

class SharedUseCase(private val sharedPrefRepository: SharedPrefRepository,onDataChangedListener: OnDataChangedListener){
    private val realmDatabaseRepository = RealmDatabaseRepository(onDataChangedListener)

    fun updateCurrentLabel(title:String){
        sharedPrefRepository.updateCurrentLabel(title)
    }
    fun getLatestLabel():String{
        return sharedPrefRepository.getLatestLabel()
    }

    fun editListTitle(newTitle: String,lastTitle:String){
        realmDatabaseRepository.editListTitle(lastTitle,newTitle)
        sharedPrefRepository.updateCurrentLabel(newTitle)
    }
}