package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.new_arch.data.RealmDatabaseRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener

class FlashCardFragmentUsecase(onDataChangedListener: OnDataChangedListener):WordListProvider{

   private val databaseRepository  = RealmDatabaseRepository(onDataChangedListener)



   override fun provideWordList(listName:String):MutableList<WordObject>{
        val wholeList = databaseRepository.loadWholeWords()
        var sortedList = mutableListOf<WordObject>()
        wholeList.forEach {
            if (it.listName ==  listName){
                sortedList.add(it)
            }
        }

        return  sortedList
    }


}