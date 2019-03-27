package com.shojishunsuke.pronounceflachcard.testFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.shojishunsuke.pronounceflachcard.R

class TestStartFragment:Fragment() {

    var questionNumber = 0
    var trueNumbersList = arrayListOf<Int>()
    var falseNumbersList = arrayListOf<Int>()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout  = inflater.inflate(R.layout.fragment_test_start,container,false)

        val startButton = layout.findViewById<Button>(R.id.testStartButton)

        val key_checked = resources.getString(R.string.key_is_checked_Only)
        val key_question_number = resources.getString(R.string.key_question_number)
        val key_true_numbers =  resources.getString(R.string.key_true_numbers)
        val key_false_numbers =resources.getString(R.string.key_false_numbers)

        val isCheckedOnly = arguments!!.getBoolean(key_checked)


        val bundle = Bundle()
        bundle.putInt(key_question_number,questionNumber)
        bundle.putBoolean(key_checked,isCheckedOnly)
        bundle.putIntegerArrayList(key_true_numbers,trueNumbersList)
        bundle.putIntegerArrayList(key_false_numbers,falseNumbersList)

        startButton.setOnClickListener {

            val testPronounceFragment = TestPronounceFragment()
            testPronounceFragment.arguments = bundle

            val fragmentTransAction = fragmentManager!!.beginTransaction()
            fragmentTransAction.addToBackStack(null)
            fragmentTransAction.replace(R.id.testPronounceBackground,testPronounceFragment)
            fragmentTransAction.commit()


        }

        return layout
    }
}