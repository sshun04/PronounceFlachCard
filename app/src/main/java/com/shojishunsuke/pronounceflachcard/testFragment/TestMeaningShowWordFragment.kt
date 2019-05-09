package com.shojishunsuke.pronounceflachcard.testFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.R


class TestMeaningShowWordFragment:Fragment() {




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout  = inflater.inflate(R.layout.fragment_test_meaning_show_word,container,false)

        val textView = layout.findViewById<TextView>(R.id.meaningTextView3)
        val key_question_number = resources.getString(R.string.key_question_number)
        val key_quesiton_words_list = resources.getString(R.string.key_question_words)


        val questionNumber = arguments!!.getInt(key_question_number)
        val questionWordsList = arguments!!.getSerializable(key_quesiton_words_list)as ArrayList<QuestionWord>


        textView.setText(questionWordsList[questionNumber].word)

        var bundle = Bundle()
        bundle.putInt(key_question_number,questionNumber)
        bundle.putSerializable(key_quesiton_words_list,questionWordsList)



        textView.setOnClickListener {
            val showAnswerFragment = TestMeaningShowAnswerFragment()
            showAnswerFragment.arguments = bundle

            val fragmentTransAction = fragmentManager!!.beginTransaction()
            fragmentTransAction.replace(R.id.testMeaningBackGround,showAnswerFragment)
            fragmentTransAction.commit()


        }



        return layout
    }
}