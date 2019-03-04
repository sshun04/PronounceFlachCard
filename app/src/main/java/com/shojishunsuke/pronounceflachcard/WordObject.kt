package com.shojishunsuke.pronounceflachcard

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class WordObject(@PrimaryKey open var id:String = UUID.randomUUID().toString(),
                      @Required open var word :String ="",
                      open var meaning : String = ""):RealmObject() {

}