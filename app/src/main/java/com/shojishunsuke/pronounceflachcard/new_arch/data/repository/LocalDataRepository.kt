package com.shojishunsuke.pronounceflachcard.new_arch.data.repository

import com.shojishunsuke.pronounceflachcard.Model.WordObject
import io.realm.RealmResults

interface LocalDataRepository {
    fun getWholeWords():List<WordObject>
    fun deleteWord(id :String)
    fun editWord(id: String,word: String,meaning: String)
    fun registerWord(word:String,meaning:String,listName :String)
}