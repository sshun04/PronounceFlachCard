package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.Model.WordObject
import io.realm.RealmResults

interface WordListProvider {
    fun provideWordList(listName:String):RealmResults<WordObject>
}