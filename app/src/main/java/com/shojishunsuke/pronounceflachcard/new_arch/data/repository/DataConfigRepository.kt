package com.shojishunsuke.pronounceflachcard.new_arch.data.repository

interface DataConfigRepository {
    fun isInitialized():Boolean
    fun getLatestLabel():String
    fun updateInitializationState()
    fun updateCurrentLabel(listTitle:String)

}