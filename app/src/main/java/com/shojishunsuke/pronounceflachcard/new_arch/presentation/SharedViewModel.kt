package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.domain.SharedUseCase

class SharedViewModel : ViewModel() {
    val currentTitle = MutableLiveData<String>()

    fun select(title:String){
        currentTitle.value = title
    }

    val title:String get() = currentTitle.value!!

}