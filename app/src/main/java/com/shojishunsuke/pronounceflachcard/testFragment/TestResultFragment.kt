package com.shojishunsuke.pronounceflachcard.testFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.activity.TestMeaningActivity

class TestResultFragment:Fragment() {

    var trueNumbersList = arrayListOf<Int>()
    var falseNumbersList = arrayListOf<Int>()
    var key_true_numbers = ""
    var key_false_numbers = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_test_result,container,false)

        val textView = layout.findViewById<TextView>(R.id.resultTextView)
        val endButton = layout.findViewById<Button>(R.id.endButton)
         key_true_numbers = resources.getString(R.string.key_true_numbers)
         key_false_numbers =resources.getString(R.string.key_false_numbers)

        trueNumbersList = arguments!!.getIntegerArrayList(key_true_numbers)
        falseNumbersList = arguments!!.getIntegerArrayList(key_false_numbers)


        textView.setText(trueNumbersList.size.toString()+ "/" + falseNumbersList.size.toString())

        endButton.setOnClickListener {


            activity!!.finish()



        }






        return layout
    }
}