package com.shojishunsuke.pronounceflachcard.new_arch.data

import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.LocalDataRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import io.realm.Realm
import io.realm.RealmResults
import java.util.*

class DatabaseRepository(onDataChangedListener: OnDataChangedListener) : LocalDataRepository{
    val realm = Realm.getDefaultInstance()

    init {
        realm.addChangeListener { onDataChangedListener.onDataChanged() }
    }

    override fun getWholeWords(): List<WordObject> {
        return  realm.where(WordObject::class.java).findAll().toList()
    }

    override fun deleteWord(id:String) {

        realm.executeTransaction {
            it.where(WordObject::class.java).equalTo("id",id).findAll().deleteAllFromRealm()
        }
    }

    override fun editWord(id: String, word: String, meaning: String) {

        realm.executeTransaction {
            var targetWord = it.where(WordObject::class.java).equalTo("id",id).findAll().first()

            targetWord!!.word = word
            targetWord.meaning = meaning

            it.copyToRealm(targetWord)

        }
    }

    override fun registerWord(word: String, meaning: String, listName :String) {
        realm.executeTransaction {

            var wordCard = it.createObject(WordObject::class.java)

            wordCard.id = UUID.randomUUID().toString()
            wordCard.word =  word
            wordCard.meaning = meaning
            wordCard.listName = listName

            it.copyToRealm(wordCard)

        }
    }


}