package com.shojishunsuke.pronounceflachcard.new_arch.data

import android.content.Context
import android.content.SharedPreferences
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.DataConfigRepository

class SharedPrefRepository(context: Context):DataConfigRepository {

    private val sharedPreferences:SharedPreferences

    private val key_label = "KEY_LABEL"
    private val key_init = "KEY_INITIALIZED"

    init {
        sharedPreferences = context.getSharedPreferences("sharedPref",Context.MODE_PRIVATE)
    }

    override fun isInitialized(): Boolean {
        return sharedPreferences.getBoolean(key_init,false)
    }

    override fun getLatestLabel(): String {
        return sharedPreferences.getString(key_label,"")
    }

    override fun updateInitializationState() {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key_init,true)
        editor.apply()
    }

    override fun updateCurrentLabel(listTitle:String) {
        val editor = sharedPreferences.edit()
        editor.putString(key_label,listTitle)
        editor.apply()
    }
}