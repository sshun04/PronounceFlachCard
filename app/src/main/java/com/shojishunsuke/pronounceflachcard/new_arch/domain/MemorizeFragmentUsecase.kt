package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.new_arch.data.RealmDatabaseRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import io.realm.RealmResults

class MemorizeFragmentUsecase(onDataChangedListener: OnDataChangedListener):WordListProvider {
    private val databaseRepository = RealmDatabaseRepository(onDataChangedListener)


   override fun provideWordList(listName:String):RealmResults<WordObject>{


        return  databaseRepository.loadListWords(listName)
    }


}