package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import com.shojishunsuke.pronounceflachcard.new_arch.domain.AddWordDialogUsecase

class AddWordDialogViewModel {
    private val addWordDialogUsecase = AddWordDialogUsecase()

    fun registerWord(word:String,meaning:String,listName : String){

        addWordDialogUsecase.registerWordToRealm(word, meaning, listName)

    }
}