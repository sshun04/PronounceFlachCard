package com.shojishunsuke.pronounceflachcard.testFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.TestPronounceFragmentViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.TestSharedViewModel
import kotlinx.android.synthetic.main.fragment_test_pronounce.*

class TestPronounceFragment : Fragment(),TestSharedViewModel.TestCompletionListener {

    lateinit var nextButton: Button
    lateinit var confirmTextView: TextView
    lateinit var questionTextView: TextView
    lateinit var fragmentViewModel: TestPronounceFragmentViewModel
    lateinit var sharedViewModel: TestSharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_test_pronounce, container, false)
        questionTextView = layout.findViewById(R.id.wordTextView4)
        nextButton = layout.findViewById(R.id.nextButton)
        confirmTextView = layout.findViewById(R.id.textView1)

        fragmentViewModel = ViewModelProviders.of(this).get(TestPronounceFragmentViewModel::class.java)
        sharedViewModel = ViewModelProviders.of(this).get(TestSharedViewModel::class.java)


        nextButton.setOnClickListener {
            setupQuestion()
        }


        setupQuestion()

        return layout
    }

    private fun setupQuestion() {

        questionTextView.setText(sharedViewModel.question)
        confirmTextView.visibility = View.VISIBLE
        nextButton.visibility = View.GONE

        fragmentViewModel.setupSpeechRecognizer(
            context!!,
            object : TestPronounceFragmentViewModel.JudgeListener {
                override fun onRecognized(recognitionResult: String) {

                    val isTrue = recognitionResult.equals(sharedViewModel.question)
                    sharedViewModel.onAnswered(isTrue,recognitionResult,this@TestPronounceFragment)

                    confirmTextView.visibility = View.GONE
                    nextButton.visibility = View.VISIBLE
                }
            })
    }

    override fun onCompleteTest() {

        confirmTextView.visibility = View.GONE
        nextButton.visibility = View.GONE
        resultButton.visibility = View.VISIBLE

        resultButton.setOnClickListener {

            sharedViewModel.setupResultFragment(fragmentManager!!)
        }
    }
}