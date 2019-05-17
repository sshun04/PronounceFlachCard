package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.new_arch.domain.TestReadyUseCase

class TestReadyViewModel {
    private val testReadyUseCase = TestReadyUseCase()

    fun getWordsList(listName: String): List<WordObject> {
        return testReadyUseCase.provideTestWordList(listName)
    }


    fun setupTestActivity() {


    }
}