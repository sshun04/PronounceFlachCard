package com.shojishunsuke.pronounceflachcard.new_arch.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.SharedViewModel

class SharedViewModelFactory(val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SharedViewModel(context) as T
    }
}