package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.data.SharedPrefRepository
import com.shojishunsuke.pronounceflachcard.new_arch.domain.SharedUseCase

class SharedViewModel(context: Context) : ViewModel() {
    val liveDataTitle = MutableLiveData<String>()


    private val key_title = "LAST_TITle"
    private val sharedUseCase: SharedUseCase

    init {

        val sharedPrefRepository = SharedPrefRepository(context)
        sharedUseCase = SharedUseCase(sharedPrefRepository)
        liveDataTitle.value = sharedUseCase.getLatestLabel()
    }

    fun select(title: String) {
        liveDataTitle.value = title
    }

    val title: String get() = liveDataTitle.value!!


    fun saveLastTitle() {
        sharedUseCase.updateCurrentLabel(liveDataTitle.value!!)
    }
}