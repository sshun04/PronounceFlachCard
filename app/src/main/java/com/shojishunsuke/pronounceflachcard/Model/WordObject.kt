package com.shojishunsuke.pronounceflachcard.Model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import java.util.*
@RealmClass
open class WordObject(
                      @Required open var word :String = "word",
                      @Required open var meaning : String = "meaning",
                       open var isDone: Boolean = false
):RealmObject() {

}