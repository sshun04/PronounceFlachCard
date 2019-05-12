package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.domain.CardRecyclerAdapaterUsecase

class CardRecyclerAdapterViewModel(onDataChangedListener: OnDataChangedListener) {

    val cardRecyclerAdapaterUsecase   = CardRecyclerAdapaterUsecase (onDataChangedListener)

    fun editWord(id:String,word:String,meaning:String){
        cardRecyclerAdapaterUsecase.editWord(id,word,meaning)
    }

    fun  deleteWord(id: String){
        cardRecyclerAdapaterUsecase.deleteWord(id)
    }
}