package com.shojishunsuke.pronounceflachcard.new_arch.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shojishunsuke.pronounceflachcard.Model.QuestionWord

class TestSharedViewModelFactory(val list:ArrayList<QuestionWord>):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TestSharedViewModelFactory(list) as T
    }
}