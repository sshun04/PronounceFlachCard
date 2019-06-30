package com.shojishunsuke.pronounceflachcard.testFragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.adapter.TestResultRecyclerViewAdapter
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.TestSharedViewModel

class TestResultFragment : Fragment() {

    lateinit var resultProgress: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_test_result, container, false)

        val textView = layout.findViewById<TextView>(R.id.resultTextView)

        val recyclerView = layout.findViewById<RecyclerView>(R.id.resultRecyclerView)

        val sharedViewModel = requireActivity().run {
            ViewModelProviders.of(this).get(TestSharedViewModel::class.java)
        }
        val resultWords = sharedViewModel.getTotalResult()

        recyclerView.adapter = TestResultRecyclerViewAdapter(requireContext(), resultWords)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)


        var trueWordsNumber = 0

        resultWords.forEach {
            if (it.isTrue) {
                trueWordsNumber++
            }
        }

        resultProgress = layout.findViewById<ProgressBar>(R.id.progressCircle).apply {
            max = resultWords.size * 100
        }

        onSetProgress(trueWordsNumber * 100)
        textView.setText("$trueWordsNumber/${resultWords.size}")
        return layout
    }

    private fun onSetProgress(progress: Int) {

        val handler = Handler()
        handler.postDelayed(Runnable {

            resultProgress.setProgress(progress, true)
        }, 300)

    }


}