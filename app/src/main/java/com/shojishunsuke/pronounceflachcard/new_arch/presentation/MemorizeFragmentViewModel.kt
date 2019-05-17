package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.domain.MemorizeFragmentUseCase

class MemorizeFragmentViewModel(onDataChangedListener: OnDataChangedListener):ViewModel(){
    private val  useCase = MemorizeFragmentUseCase(onDataChangedListener)

    fun getWordsList(listName:String):MutableList<WordObject>{
        return useCase.provideWordList(listName)
    }

}