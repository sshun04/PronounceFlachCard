package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.new_arch.data.RealmDatabaseRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener

class DrawerUsecase(onDataChangedListener: OnDataChangedListener) {
    val databaseRepository = RealmDatabaseRepository(onDataChangedListener)

    fun  loadWholeTitleList():List<String>{
        val titleList  = mutableListOf<String>()
        databaseRepository.loadTitleList().forEach {
            titleList.add(it.title)
        }
        return titleList
    }

    fun regitsterNewListTitle(title:String){
        databaseRepository.registerListTitle(title)
    }
}