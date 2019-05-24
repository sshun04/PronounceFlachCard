package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.testFragment.TestResultFragment

class TestSharedViewModel(private val testList: ArrayList<QuestionWord>) : ViewModel() {

    private var currentNumber = 0

    val question: String get() = testList[currentNumber].word
    val answer: String get() = testList[currentNumber].meaning
    var hasNextQuestion = true

    fun onAnswered(isTrue: Boolean, recognizedWord: String, completionListener: TestCompletionListener) {

        testList[currentNumber].apply {
            this.isTrue = isTrue
            if (isTrue) {
                this.recognizedWord = question
            } else {
                this.recognizedWord = recognizedWord
            }

        }
        currentNumber++

        if (currentNumber == testList.size) {
            completionListener.onCompleteTest()
        }


    }

    fun onAnswered(isTrue: Boolean, completionListener: TestCompletionListener) {
        testList[currentNumber].isTrue = isTrue
        currentNumber++

        if (currentNumber == testList.size) {

            hasNextQuestion = false
            completionListener.onCompleteTest()
        }
    }

    fun setupResultFragment(supportFragmentManager: FragmentManager, backgroundResource: Int) {
        val resultFragment = TestResultFragment()

        val fragmentTransAction = supportFragmentManager.beginTransaction()
        fragmentTransAction.addToBackStack(null)
        fragmentTransAction.replace(backgroundResource, resultFragment)
        fragmentTransAction.commit()

    }

    fun getTotalResult(): ArrayList<QuestionWord> = testList


    interface TestCompletionListener {
        fun onCompleteTest()
    }

}