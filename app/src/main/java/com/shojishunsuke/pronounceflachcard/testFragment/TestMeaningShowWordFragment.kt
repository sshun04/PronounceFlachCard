package com.shojishunsuke.pronounceflachcard.testFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.SharedViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.TestSharedViewModel


class TestMeaningShowWordFragment:Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout  = inflater.inflate(R.layout.fragment_test_meaning_show_word,container,false)

        val textView = layout.findViewById<TextView>(R.id.meaningTextView3)

        val sharedViewModel = activity?.run {
            ViewModelProviders.of(this).get(TestSharedViewModel::class.java)
        }?: throw Exception("Invalid Activity")

        textView.setText(sharedViewModel.question)

        textView.setOnClickListener {
            val showAnswerFragment = TestMeaningShowAnswerFragment()
            val fragmentTransAction = fragmentManager!!.beginTransaction()
            fragmentTransAction.replace(R.id.testMeaningBackGround,showAnswerFragment)
            fragmentTransAction.commit()

        }
        return layout
    }
}