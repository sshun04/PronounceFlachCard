package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.domain.EditDialogUseCase

class EditDialogViewModel(onDataChangedListener: OnDataChangedListener) :ViewModel(){

    private val editDialogUseCase = EditDialogUseCase(onDataChangedListener)

    fun editWord(id: String, word: String, meaning: String) {
        editDialogUseCase.editWordInformation(id, word, meaning)
    }
}