package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.domain.MemorizeFragmentUsecase
import io.realm.RealmResults

class MemorizeFragmentViewModel(onDataChangedListener: OnDataChangedListener):ViewModel(){
    private val  useCase = MemorizeFragmentUsecase(onDataChangedListener)

    fun getWordsList(listName:String):RealmResults<WordObject>{
        return useCase.provideWordList(listName)
    }

}