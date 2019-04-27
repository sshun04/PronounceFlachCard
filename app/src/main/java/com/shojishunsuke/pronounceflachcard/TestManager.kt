package com.shojishunsuke.pronounceflachcard

import android.widget.Toast
import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import io.realm.Realm
import io.realm.RealmResults
import java.util.*
import kotlin.collections.ArrayList

class TestManager(val testFormat:Int ,val testRange:Int,val testOrder:Int) {

    val realm = Realm.getDefaultInstance()
    lateinit var testList :RealmResults<WordObject>

   open fun createTestList():ArrayList<QuestionWord>{

       val testArrayList = ArrayList<QuestionWord>()

       when(testRange){
           R.id.allWords -> testList = realm.where(WordObject::class.java).findAll()
           R.id.checkedWord -> testList = realm.where(WordObject::class.java).equalTo("isDone",true).findAll()
           else -> {}
       }

       for (word in testList){
           val arrayWord = QuestionWord()
           arrayWord.meaning = word.meaning
           arrayWord.word = word.word

           arrayWord.isPronounce = (testFormat == R.id.pronounce)

           testArrayList.add(arrayWord)
       }

       if (testOrder == R.id.random){
           testArrayList.shuffle()
       }


        return testArrayList
    }


}