package com.shojishunsuke.pronounceflachcard.Model

import io.realm.RealmObject
import io.realm.annotations.Required

class FlashCardTitle(@Required open var title : String = "") : RealmObject() {
}