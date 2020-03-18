package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.new_arch.data.RealmDatabaseRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.utility.TestListProvider

class TestReadyUsecase : OnDataChangedListener {
    private val databaseRepository = RealmDatabaseRepository(this)

    fun getCurrentListSize(listTitle: String, isChecked: Boolean): Int {

        val wordList = databaseRepository.loadWordList(listTitle)
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
        listTitle: String,
        listSize: Int,
        testRange: Int,
        isRandom: Boolean
    ): ArrayList<QuestionWord> {
        val realmListList = databaseRepository.loadWordList(listTitle)

        val testListProvider = TestListProvider(realmListList)

        return testListProvider.createTestList(listSize, testRange, isRandom)
    }

    override fun onDataChanged() {

    }
}