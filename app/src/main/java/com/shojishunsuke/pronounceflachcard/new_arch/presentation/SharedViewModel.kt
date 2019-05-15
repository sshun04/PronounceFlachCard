package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.domain.SharedUseCase

class SharedViewModel(onDataChangedListener: OnDataChangedListener):ViewModel() {
    private val listNames:List<String>
    private val currentTitle  = MutableLiveData<String>()
    private val useCase = SharedUseCase(onDataChangedListener)


    init {
     listNames = useCase.getWholeTitleList()
    }

    val currentTilte:LiveData<String> get() = currentTitle








}