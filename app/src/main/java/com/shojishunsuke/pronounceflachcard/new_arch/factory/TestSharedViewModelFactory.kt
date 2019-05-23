package com.shojishunsuke.pronounceflachcard.new_arch.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.TestSharedViewModel

class TestSharedViewModelFactory(val list:ArrayList<QuestionWord>):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TestSharedViewModel(list) as T
    }
}