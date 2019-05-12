package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.new_arch.data.DatabaseRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener

class FlashCardFragmentUsecase:OnDataChangedListener{

   private val databaseRepository  = DatabaseRepository(this)



    fun provideSortedList(listName:String):List<WordObject>{
        val wholeList = databaseRepository.getWholeWords()
        var sortedList = mutableListOf<WordObject>()
        wholeList.forEach {
            if (it.listName ==  listName){
                sortedList.add(it)
            }
        }

        return  sortedList
    }

    override fun onDataChanged() {

    }
}