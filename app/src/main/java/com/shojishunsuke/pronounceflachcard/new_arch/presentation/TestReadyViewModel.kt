package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.new_arch.domain.TestReadyUsecase

class TestReadyViewModel {
    private val testReadyUseCase = TestReadyUsecase()

    fun getWordsList(listName: String): List<WordObject> {
        return testReadyUseCase.provideTestWordList(listName)
    }


    fun setupTestActivity() {


    }
}