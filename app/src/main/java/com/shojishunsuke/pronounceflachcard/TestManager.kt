package com.shojishunsuke.pronounceflachcard

import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import io.realm.Realm
import io.realm.RealmResults

class TestManager {

    private val realm = Realm.getDefaultInstance()
    lateinit var testList: RealmResults<WordObject>

    fun createTestList(testFormat: Int, testRange: Int, isRandom:Boolean, listSize: Int): ArrayList<QuestionWord> {

        var testArrayList = ArrayList<QuestionWord>()

        when (testRange) {
            R.id.allWords -> testList = realm.where(WordObject::class.java).findAll()
            R.id.checkedWord -> testList = realm.where(WordObject::class.java).equalTo("isDone", true).findAll()
            else -> {
            }
        }

        for (word in testList) {
            val arrayWord = QuestionWord()
            arrayWord.meaning = word.meaning
            arrayWord.word = word.word

            arrayWord.isPronounce = (testFormat == R.id.pronounce)

            testArrayList.add(arrayWord)
        }



        if ( isRandom)
            testArrayList.shuffle()


        val differ = testArrayList.size - listSize

        return testArrayList.dropLast(differ) as ArrayList<QuestionWord>
    }

    fun getCurrentListSize(range: Int): Int = if (range == R.id.allWords)
        realm.where(WordObject::class.java).findAll().size
    else
        realm.where(WordObject::class.java).equalTo("isDone", true).findAll().size


}