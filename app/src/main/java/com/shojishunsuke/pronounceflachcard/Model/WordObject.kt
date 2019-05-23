package com.shojishunsuke.pronounceflachcard.Model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import java.util.*
@RealmClass
open class WordObject(@Required open  var id  :String = "",
                      @Required open var word :String = "word",
                      @Required open var meaning : String = "meaning",
                      @Required open var listTitle :String = "",
                      open var isDone: Boolean = false
):RealmObject() {

}