package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.domain.CardRecyclerViewAdapterUsecase

class CardRecyclerAdapterViewModel(private val onDataChangedListener: OnDataChangedListener):ViewModel() {


   private val cardRecyclerAdapterUsecase   = CardRecyclerViewAdapterUsecase (onDataChangedListener)

    fun editWord(id:String,word:String,meaning:String){
        cardRecyclerAdapterUsecase.editWord(id,word,meaning)
    }

    fun  deleteWord(id: String){
        cardRecyclerAdapterUsecase.deleteWord(id)
    }


}