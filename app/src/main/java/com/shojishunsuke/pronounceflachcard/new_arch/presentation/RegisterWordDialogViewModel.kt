package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.domain.RegisterWordDialogUseCase

class RegisterWordDialogViewModel(onDataChangedListener: OnDataChangedListener):ViewModel() {
    private val addWordDialogUsecase = RegisterWordDialogUseCase(onDataChangedListener)

    fun registerWord(word:String,meaning:String,listName : String){

        addWordDialogUsecase.registerWordToRealm(word, meaning, listName)

    }
}