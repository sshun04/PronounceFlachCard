package com.shojishunsuke.pronounceflachcard

import com.shojishunsuke.pronounceflachcard.Model.WordObject
import io.realm.Realm
import io.realm.RealmResults

object FlashCardManager {
 val realm = Realm.getDefaultInstance()
    init {
//        初期化のタイミングで最初にどのリストを表示するのか処理する
//        あらかじめrealmに最後に開かれていたリストがどれかを保存しておく
    }

    fun getCurrentList():RealmResults<WordObject>{
        return realm.where(WordObject::class.java).findAll()
    }

}