package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.Model.FlashCardTitle
import com.shojishunsuke.pronounceflachcard.new_arch.data.RealmDatabaseRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import io.realm.RealmResults

class DrawerUsecase(onDataChangedListener: OnDataChangedListener) {
    private val databaseRepository = RealmDatabaseRepository(onDataChangedListener)

    fun  loadWholeTitleList():RealmResults<FlashCardTitle>{
        return databaseRepository.loadTitleList()
    }

    fun registerNewListTitle(title:String){
        databaseRepository.registerListTitle(title)
    }
    fun deleteListTitle(title: String){
        databaseRepository.deleteList(title)
    }
}