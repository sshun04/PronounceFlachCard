package com.shojishunsuke.pronounceflachcard.new_arch.data

import com.shojishunsuke.pronounceflachcard.Model.FlashCardTitle
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.LocalDataRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import io.realm.Realm
import io.realm.RealmResults
import java.util.*

class RealmDatabaseRepository(onDataChangedListener: OnDataChangedListener) : LocalDataRepository {
    private val realm = Realm.getDefaultInstance()

    init {
        realm.addChangeListener { onDataChangedListener.onDataChanged() }
    }

    override fun loadListWords(listTitle: String): RealmResults<WordObject> {
        return realm.where(WordObject::class.java).equalTo("listTitle",listTitle).findAll()
    }

    override fun deleteWord(id: String) {

        realm.executeTransaction {
            it.where(WordObject::class.java).equalTo("id", id).findFirst()?.deleteFromRealm()
        }
    }

    override fun editWord(id: String, word: String, meaning: String) {

        realm.executeTransaction {
            var targetWord = it.where(WordObject::class.java).equalTo("id", id).findFirst()

            targetWord!!.word = word
            targetWord.meaning = meaning

            it.copyToRealm(targetWord)

        }
    }

    override fun registerWord(word: String, meaning: String, listName: String) {
        realm.executeTransaction {

            val wordCard = it.createObject(WordObject::class.java)

            wordCard.id = UUID.randomUUID().toString()
            wordCard.word = word
            wordCard.meaning = meaning
            wordCard.listTitle = listName

            it.copyToRealm(wordCard)

        }
    }

    override fun switchWhetherChecked(id: String, isChecked: Boolean) {

        realm.executeTransaction {
            val wordCard = it.where(WordObject::class.java).equalTo("id", id).findFirst()

            wordCard!!.isDone = isChecked

            it.copyToRealm(wordCard)

        }
    }

    override fun registerListTitle(title: String) {

        realm.executeTransaction {

            val listTitle = it.createObject(FlashCardTitle::class.java)

            listTitle.title = title

            it.copyToRealm(listTitle)
        }

    }

    override fun loadTitleList(): List<FlashCardTitle> {

        return realm.where(FlashCardTitle::class.java).findAll().toList()
    }

    override fun deleteList(listTitle: String) {

        realm.executeTransaction {
            val list = it.where(WordObject::class.java).equalTo("listTitle",listTitle).findAll()
            list.deleteAllFromRealm()
        }
    }




}