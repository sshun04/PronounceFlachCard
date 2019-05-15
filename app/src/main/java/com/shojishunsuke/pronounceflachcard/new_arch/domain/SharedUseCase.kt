package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.Model.FlashCardTitle
import com.shojishunsuke.pronounceflachcard.new_arch.data.RealmDatabaseRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener

class SharedUseCase(onDataChangedListener:OnDataChangedListener) {
    private val databaseRepository = RealmDatabaseRepository(onDataChangedListener)

    fun  getWholeTitleList():List<String>{
        val titleList  = mutableListOf<String>()
        databaseRepository.getTitleList().forEach {
            titleList.add(it.title)
        }
        return titleList
    }
}