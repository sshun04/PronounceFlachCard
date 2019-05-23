package com.shojishunsuke.pronounceflachcard.testFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.button.MaterialButton
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.SharedViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.TestReadyViewModel

class TestReadyFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var testFormat = -1
    private var testRange = -1
    private var listSize = 1
    private var isRandom = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_test_ready, container, false)
        val formatGroup = layout.findViewById<RadioGroup>(R.id.radioGroupFormat)
        val rangeGroup = layout.findViewById<RadioGroup>(R.id.radioGroupRange)
        val numberSpinner = layout.findViewById<androidx.appcompat.widget.AppCompatSpinner>(R.id.numberDropDown)
        val startButton = layout.findViewById<MaterialButton>(R.id.testStartButton)
        val randomSwitch = layout.findViewById<androidx.appcompat.widget.SwitchCompat>(R.id.randomSwitch)

        val viewModel = ViewModelProviders.of(this).get(TestReadyViewModel::class.java)
      
       val sharedViewModel = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        }?: throw Exception("Invalid Activity")

        randomSwitch.setOnCheckedChangeListener { _, boolean -> isRandom = boolean }

        formatGroup.setOnCheckedChangeListener { _, i -> testFormat = i }
        rangeGroup.setOnCheckedChangeListener { _, i ->

            testRange = i

            val sizeLimit = viewModel.getCurrentListSize(testRange)

            val testList = ArrayList<Int>()

            for (i in 1 until sizeLimit) {
                testList.add(i)
            }


            val spinnerAdapter = ArrayAdapter(context!!, R.layout.support_simple_spinner_dropdown_item, testList)
            numberSpinner.adapter = spinnerAdapter

        }

        numberSpinner.onItemSelectedListener = this


        startButton.setOnClickListener {



            if (testRange != -1 && testFormat != -1) {

                viewModel.setupTestActivity(
                    context!!,
                    listSize = listSize,
                    testFormat = testFormat,
                    testRange = testRange,
                    isRandom = isRandom,
                    listTitle = sharedViewModel.title
                )

            } else {
                Toast.makeText(context, "指定していない項目があります", Toast.LENGTH_SHORT).show()

            }
        }
        return layout
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        listSize = position + 1
    }
}