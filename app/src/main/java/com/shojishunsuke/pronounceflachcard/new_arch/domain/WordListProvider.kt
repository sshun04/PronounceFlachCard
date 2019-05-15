package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.Model.WordObject

interface WordListProvider {
    fun provideWordList(listName:String):List<WordObject>
}