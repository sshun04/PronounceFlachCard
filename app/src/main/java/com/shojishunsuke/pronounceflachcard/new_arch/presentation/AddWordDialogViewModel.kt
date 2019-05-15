package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.domain.AddWordDialogUseCase

class AddWordDialogViewModel(onDataChangedListener: OnDataChangedListener):ViewModel() {
    private val addWordDialogUsecase = AddWordDialogUseCase(onDataChangedListener)

    fun registerWord(word:String,meaning:String,listName : String){

        addWordDialogUsecase.registerWordToRealm(word, meaning, listName)

    }
}