package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.new_arch.data.RealmDatabaseRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener

class SharedUseCase(onDataChangedListener:OnDataChangedListener) {
    private val databaseRepository = RealmDatabaseRepository(onDataChangedListener)

}