package com.shojishunsuke.pronounceflachcard.testFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.shojishunsuke.pronounceflachcard.R

class TestResultFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_test_result, container, false)

        val textView = layout.findViewById<TextView>(R.id.resultTextView)
        val endButton = layout.findViewById<Button>(R.id.endButton)
        val key_true_numbers = resources.getString(R.string.key_true_numbers)
        val key_false_numbers = resources.getString(R.string.key_false_numbers)
        val key_recognized_words = resources.getString(R.string.key_recognized_words)

        val recyclerView = layout.findViewById<RecyclerView>(R.id.resultRecyclerView)






        val trueNumbersList = arguments!!.getIntegerArrayList(key_true_numbers)
        val falseNumbersList = arguments!!.getIntegerArrayList(key_false_numbers)
        val isPronounce = arguments!!.getBoolean("isPronounce")



        textView.setText(trueNumbersList.size.toString() + "問正解")

        endButton.setOnClickListener {


            activity!!.finish()


        }






        return layout
    }
}