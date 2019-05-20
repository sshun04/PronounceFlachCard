package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.new_arch.data.RealmDatabaseRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener

class CardRecyclerViewAdapterUsecase(onDataChangedListener: OnDataChangedListener)  {

   private val dataRepository = RealmDatabaseRepository(onDataChangedListener)

    fun editWord(id:String,word:String,meaning:String){
        dataRepository.editWord(id,word,meaning)
    }

    fun deleteWord(id : String){
        dataRepository.deleteWord(id)
    }
}