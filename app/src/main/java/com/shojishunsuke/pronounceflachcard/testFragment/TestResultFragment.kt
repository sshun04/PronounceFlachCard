package com.shojishunsuke.pronounceflachcard.testFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.adapter.TestRecyclerViewAdapter

class TestResultFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_test_result, container, false)

        val textView = layout.findViewById<TextView>(R.id.resultTextView)
        val endButton = layout.findViewById<Button>(R.id.endButton)
       val key_quesiton_words = resources.getString(R.string.key_question_words)

        val recyclerView = layout.findViewById<RecyclerView>(R.id.resultRecyclerView)

        val resultWords = arguments!!.getSerializable(key_quesiton_words) as ArrayList<QuestionWord>

        recyclerView.adapter = TestRecyclerViewAdapter(context, resultWords)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)


        var trueWordsNumber: Int = 0

        resultWords.forEach {
            if (it.isTrue) {

                trueWordsNumber++

            }
        }

        textView.setText(trueWordsNumber.toString()+"問正解")


        return layout
    }
}