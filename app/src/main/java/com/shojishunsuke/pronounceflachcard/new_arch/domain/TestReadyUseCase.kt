package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.new_arch.data.RealmDatabaseRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener

class TestReadyUseCase : OnDataChangedListener {
   private val databaseRepository = RealmDatabaseRepository(this)

    //   maybe-later ここでテストに必要な単語だけにソートする
    fun provideTestWordList(listName: String): List<WordObject> {
        val wholeList = databaseRepository.getWholeWords()
        var sortedList = mutableListOf<WordObject>()
        wholeList.forEach {
            if (it.listName == listName) {
                sortedList.add(it)
            }
        }

        return sortedList
    }

    override fun onDataChanged() {

    }
}