package com.shojishunsuke.pronounceflachcard.new_arch.data.repository

import io.realm.RealmObject
import io.realm.RealmResults

interface OnDataChangedListener {
    fun  onDataChanged()
}