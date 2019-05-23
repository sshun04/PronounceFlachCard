package com.shojishunsuke.pronounceflachcard.new_arch.data.repository

import com.shojishunsuke.pronounceflachcard.Model.FlashCardTitle
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import io.realm.RealmResults

interface LocalDataRepository {
    fun loadWordList(listTitle: String):RealmResults<WordObject>
    fun deleteWord(id :String)
    fun editWord(id: String,word: String,meaning: String)
    fun registerWord(word:String,meaning:String,listName :String)
    fun switchWhetherChecked(id: String,isChecked:Boolean)
    fun registerListTitle(title: String)
    fun loadTitleList():List<FlashCardTitle>
    fun deleteList(listTitle:String)
}