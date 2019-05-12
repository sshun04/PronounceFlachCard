package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.new_arch.data.DatabaseRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener

class CardRecyclerAdapaterUsecase(onDataChangedListener: OnDataChangedListener)  {

    val dataRepository = DatabaseRepository(onDataChangedListener)

    fun editWord(id:String,word:String,meaning:String){
        dataRepository.editWord(id,word,meaning)
    }

    fun deleteWord(id : String){
        dataRepository.deleteWord(id)
    }
}