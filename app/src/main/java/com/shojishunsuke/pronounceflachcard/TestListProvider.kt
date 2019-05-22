package com.shojishunsuke.pronounceflachcard

import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import io.realm.Realm

class TestListProvider(private val wordsList: List<WordObject>) {

    lateinit var testList: MutableList<WordObject>

    fun createTestList( listSize:Int,testRange: Int, isRandom: Boolean): ArrayList<QuestionWord> {

        var testArrayList = ArrayList<QuestionWord>()

        if (testRange == R.id.checkedWord) {
            wordsList.forEach { wordObject ->
                if (wordObject.isDone) testList.add(wordObject)
            }
        }else{
            wordsList.forEach { wordObject ->
                testList.add(wordObject)
            }
        }


        for (word in testList) {
            val arrayWord = QuestionWord()
            arrayWord.meaning = word.meaning
            arrayWord.word = word.word

            testArrayList.add(arrayWord)
        }

        if (isRandom) testArrayList.shuffle()


        val differ = testArrayList.size - listSize

        return testArrayList.dropLast(differ) as ArrayList<QuestionWord>
    }

}