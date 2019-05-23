package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.activity.TestMeaningActivity
import com.shojishunsuke.pronounceflachcard.activity.TestPronounceActivity
import com.shojishunsuke.pronounceflachcard.new_arch.domain.TestReadyUsecase

class TestReadyViewModel : ViewModel() {

    private val testReadyUsecase = TestReadyUsecase()

    fun getCurrentListSize(listTitle: String,testRange: Int): Int {
        val isChecked = testRange == R.id.checkedWord
        return testReadyUsecase.getCurrentListSize(listTitle,isChecked)
    }


    fun setupTestActivity(context: Context,listSize:Int, listTitle: String, testFormat: Int, testRange: Int, isRandom: Boolean) {

        val testWordList = testReadyUsecase.provideTestWordList(listTitle,listSize, testRange, isRandom)


        if (testFormat == R.id.pronounce) {

            val intent = Intent(context, TestPronounceActivity::class.java)
            intent.putExtra("list", testWordList)
            context.startActivity(intent)

        } else {

            val intent = Intent(context, TestMeaningActivity::class.java)
            intent.putExtra("list", testWordList)
            context.startActivity(intent)

        }
    }
}