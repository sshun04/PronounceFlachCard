package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.new_arch.data.DatabaseRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener

class AddWordDialogUsecase : OnDataChangedListener {

    private val databaseRepository = DatabaseRepository(this)

    fun registerWordToRealm(word:String,meaning:String,listName:String){
        databaseRepository.registerWord(word,meaning,listName)
    }

    override fun onDataChanged() {

    }
}