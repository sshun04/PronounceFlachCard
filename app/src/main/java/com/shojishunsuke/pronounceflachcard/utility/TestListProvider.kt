package com.shojishunsuke.pronounceflachcard.utility

import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.R

class TestListProvider(private val wordsList: List<WordObject>) {

     val testList: MutableList<WordObject> = mutableListOf()

    fun createTestList( listSize:Int,testRange: Int, isRandom: Boolean): ArrayList<QuestionWord> {

        val testArrayList = ArrayList<QuestionWord>()

        if (testRange == R.id.checkedWord) {
            wordsList.forEach { wordObject ->
                if (wordObject.isDone) testList.add(wordObject)
            }
        }else{
            wordsList.forEach { wordObject ->
                testList.add(wordObject)
            }
        }

        if (isRandom) testList.shuffle()

//        for (word in testList) {
//            val arrayWord = QuestionWord()
//            arrayWord.meaning = word.meaning
//            arrayWord.word = word.word
//
//            testArrayList.add(arrayWord)
//        }
//
        for (i in 0 until listSize){
            val arrayWord = QuestionWord()
            arrayWord.word = testList[i].word
            arrayWord.meaning = testList[i].meaning

            testArrayList.add(arrayWord)
        }



       return  testArrayList
    }

}