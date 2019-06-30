package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.data.SharedPrefRepository
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.domain.SharedUseCase

class SharedViewModel(context: Context) : ViewModel() ,OnDataChangedListener{
    val liveDataTitle = MutableLiveData<String>()

    private val sharedUseCase: SharedUseCase
    init {

        val sharedPrefRepository = SharedPrefRepository(context)
        sharedUseCase = SharedUseCase(sharedPrefRepository,this)
        liveDataTitle.value = sharedUseCase.getLatestLabel()
    }

    fun select(title: String) {
        liveDataTitle.value = title
    }

    val title: String get() = liveDataTitle.value!!

    fun editListTitle(title: String){
        sharedUseCase.editListTitle(title,liveDataTitle.value!!)
        liveDataTitle.value = title
    }

    fun saveLastTitle() {
        sharedUseCase.updateCurrentLabel(liveDataTitle.value!!)
    }

    override fun onDataChanged() {

    }
}