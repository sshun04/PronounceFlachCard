package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.TestListProvider
import com.shojishunsuke.pronounceflachcard.new_arch.data.RealmDatabaseRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener

class TestReadyUsecase : OnDataChangedListener {
    private val databaseRepository = RealmDatabaseRepository(this)

    fun getCurrentListSize(isChecked: Boolean): Int {

        val wordList = databaseRepository.loadWholeWords()
        var questionNumber = 0


        if (isChecked) {
            wordList.forEach {
                if (it.isDone) questionNumber++
            }
        } else {
            questionNumber = wordList.size

        }


        return questionNumber
    }

    //   maybe-later ここでテストに必要な単語だけにソートする
    fun provideTestWordList(
        listName: String,
        listSize:Int,
        testRange: Int,
        isRandom: Boolean
    ): ArrayList<QuestionWord> {
        val wholeList = databaseRepository.loadWholeWords()
        var sortedList = mutableListOf<WordObject>()
        wholeList.forEach {
            if (it.listName == listName) {
                sortedList.add(it)
            }
        }

        val testListProvider = TestListProvider(sortedList)

        return testListProvider.createTestList(listSize,testRange, isRandom)
    }

    override fun onDataChanged() {

    }
}