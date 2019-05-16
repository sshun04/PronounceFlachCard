package com.shojishunsuke.pronounceflachcard.Model

import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class FlashCardTitle(@Required open var title : String = "") : RealmObject() {
}