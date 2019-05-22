package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel(context: Context) : ViewModel() {
   val currentTitle = MutableLiveData<String>()

    private val key_title = "LAST_TITle"
    val sharedPreferences:SharedPreferences

    init {
       sharedPreferences = context.getSharedPreferences("a",Context.MODE_PRIVATE)
        currentTitle.value = sharedPreferences.getString(key_title,"")
    }

    fun select(title:String){
        currentTitle.value = title
    }

    val title:String get() = currentTitle.value!!


    fun saveLastTitle(){
        val editor = sharedPreferences.edit()
        editor.putString(key_title,currentTitle.value)
        editor.apply()
    }


}