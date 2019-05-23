package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.new_arch.data.RealmDatabaseRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener

class DrawerUsecase(onDataChangedListener: OnDataChangedListener) {
    private val databaseRepository = RealmDatabaseRepository(onDataChangedListener)

    fun  loadWholeTitleList():MutableList<String>{
        val titleList  = mutableListOf<String>()
        databaseRepository.loadTitleList().forEach {
            titleList.add(it.title)
        }
        return titleList
    }

    fun registerNewListTitle(title:String){
        databaseRepository.registerListTitle(title)
    }
    fun deleteListTitle(title: String){

        databaseRepository.deleteList(title)
    }
}