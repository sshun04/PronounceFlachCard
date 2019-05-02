package com.shojishunsuke.pronounceflachcard.testFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.TestManager
import com.shojishunsuke.pronounceflachcard.activity.TestMeaningActivity
import com.shojishunsuke.pronounceflachcard.activity.TestPronounceActivity

class TestReadyFragment : Fragment() {

    private var testFormat = -1
    private var testRange = -1
    private var testOrder = -1
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_test_ready, container, false)
        val formatGroup = layout.findViewById<RadioGroup>(R.id.radioGroupFormat)
        val rangeGroup = layout.findViewById<RadioGroup>(R.id.radioGroupRange)
        val orderGroup = layout.findViewById<RadioGroup>(R.id.radioGroupOrder)
        val startButton = layout.findViewById<MaterialButton>(R.id.testStartButton)
        val numberpicker = layout.findViewById<NumberPicker>(R.id.numberPicker)

        val key_quesiton_words = resources.getString(R.string.key_question_words)

        val testManager = TestManager()

        formatGroup.setOnCheckedChangeListener { _, i -> testFormat = i }
        rangeGroup.setOnCheckedChangeListener { _, i ->
            testRange = i

            numberpicker.minValue = 1
            numberpicker.maxValue = testManager.getCurrentListSize(testRange)

        }
        orderGroup.setOnCheckedChangeListener { _, i -> testOrder = i }




        startButton.setOnClickListener {

            if (testRange != -1 && testFormat != -1 && testOrder != -1) {


                val testList = testManager.createTestList(testFormat, testRange, testOrder, numberpicker.value)

                if (testFormat == R.id.pronounce) {
                    val intent = Intent(context, TestPronounceActivity::class.java)
                    intent.putExtra(key_quesiton_words, testList)

                    context!!.startActivity(intent)

                } else {
                    val intent = Intent(context, TestMeaningActivity::class.java)
                    intent.putExtra(key_quesiton_words, testList)

                    context!!.startActivity(intent)
                }

            } else {
                Toast.makeText(context, "指定していない項目があります", Toast.LENGTH_SHORT).show()

            }
        }
        return layout
    }

}