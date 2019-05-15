package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.new_arch.data.RealmDatabaseRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener

class AddWordDialogUseCase(onDataChangedListener: OnDataChangedListener)   {

    private val databaseRepository = RealmDatabaseRepository(onDataChangedListener)

    fun registerWordToRealm(word:String,meaning:String,listName:String){
        databaseRepository.registerWord(word,meaning,listName)
    }


}