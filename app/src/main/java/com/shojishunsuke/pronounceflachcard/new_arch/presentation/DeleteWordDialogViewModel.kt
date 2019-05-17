package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.domain.DeleteDialogUseCase

class DeleteWordDialogViewModel(onDataChangedListener: OnDataChangedListener) :ViewModel(){
   private val useCase = DeleteDialogUseCase(onDataChangedListener)

    fun deleteWord(wordId:String){

        useCase.deleteWordFromRealm(wordId)

    }
}