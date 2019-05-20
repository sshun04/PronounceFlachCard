package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.new_arch.data.RealmDatabaseRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener

class EditDialogUsecase (onDataChangedListener: OnDataChangedListener){
   private val databaseRepository =  RealmDatabaseRepository(onDataChangedListener)

    fun editWordInformation(id:String,word:String,meaning:String){
        databaseRepository.editWord(id, word, meaning)
    }
}