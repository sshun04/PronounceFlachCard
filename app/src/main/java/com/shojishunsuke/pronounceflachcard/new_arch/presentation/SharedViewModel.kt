package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel(context: Context) : ViewModel() {
   val liveDataTitle = MutableLiveData<String>()

    private val key_title = "LAST_TITle"
   private val sharedPreferences:SharedPreferences
    init {
        sharedPreferences = context.getSharedPreferences("a",Context.MODE_PRIVATE)
        liveDataTitle.value = sharedPreferences.getString(key_title,"")
    }

    fun select(title:String){
        liveDataTitle.value = title
    }

    val title:String get() = liveDataTitle.value!!


    fun saveLastTitle(){
        val editor = sharedPreferences.edit()
        editor.putString(key_title,liveDataTitle.value)
        editor.apply()
    }
}