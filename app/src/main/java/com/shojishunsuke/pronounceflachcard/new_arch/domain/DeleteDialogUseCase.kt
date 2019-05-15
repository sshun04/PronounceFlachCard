package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.new_arch.data.RealmDatabaseRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener

class DeleteDialogUseCase(onDataChangedListener: OnDataChangedListener) {
    private val dataRepository  = RealmDatabaseRepository(onDataChangedListener)

    fun deleteWordFromRealm(wordId:String){
        dataRepository.deleteWord(wordId)
    }
}