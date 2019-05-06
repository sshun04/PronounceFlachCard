package com.shojishunsuke.pronounceflachcard.testFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.TestManager
import com.shojishunsuke.pronounceflachcard.activity.TestMeaningActivity
import com.shojishunsuke.pronounceflachcard.activity.TestPronounceActivity

class TestReadyFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var testFormat = -1
    private var testRange = -1

    lateinit var arrayAdapter: ArrayAdapter<Int>
    lateinit var testList: ArrayList<Int>
    var listSize = 0

    private var isRandom = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_test_ready, container, false)
        val formatGroup = layout.findViewById<RadioGroup>(R.id.radioGroupFormat)
        val rangeGroup = layout.findViewById<RadioGroup>(R.id.radioGroupRange)
        val numberSpinner = layout.findViewById<androidx.appcompat.widget.AppCompatSpinner>(R.id.numberDropDown)
        val startButton = layout.findViewById<MaterialButton>(R.id.testStartButton)
        val numberpicker = layout.findViewById<NumberPicker>(R.id.numberPicker)
        val randomSwitch = layout.findViewById<androidx.appcompat.widget.SwitchCompat>(R.id.randomSwitch)



        randomSwitch.setOnCheckedChangeListener { _, boolean -> isRandom = boolean }


        val key_quesiton_words = resources.getString(R.string.key_question_words)

        val testManager = TestManager()

        formatGroup.setOnCheckedChangeListener { _, i -> testFormat = i }
        rangeGroup.setOnCheckedChangeListener { _, i ->

            testRange = i

            val sizeLimit = testManager.getCurrentListSize(testRange)

            testList = ArrayList()

            for (i in 1..sizeLimit) {
                testList.add(i)
            }

            arrayAdapter = ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, testList)
            numberSpinner.adapter = arrayAdapter


        }

        numberSpinner.onItemSelectedListener = this






        startButton.setOnClickListener {

            if (testRange != -1 && testFormat != -1) {


                val testList = testManager.createTestList(testFormat, testRange, isRandom, listSize)

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

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, positio: Int, p3: Long) {
        listSize = positio + 1
    }
}