package com.shojishunsuke.pronounceflachcard.testFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.TestSharedViewModel
import io.realm.Realm
import io.realm.RealmResults

class TestMeaningShowAnswerFragment : Fragment(), View.OnClickListener,TestSharedViewModel.TestCompletionListener {

    lateinit var textView: TextView
    lateinit var trueButton: Button
    lateinit var falseButton: Button


    lateinit var sharedViewModel: TestSharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_test_meaning_show_answer, container, false)

        textView = layout.findViewById(R.id.textView2)
        trueButton = layout.findViewById(R.id.trueButton)
        falseButton = layout.findViewById(R.id.falseButton)

     sharedViewModel = ViewModelProviders.of(this).get(TestSharedViewModel::class.java)

        textView.setText(sharedViewModel.answer)

        trueButton.setOnClickListener(this)
        falseButton.setOnClickListener(this)

        return layout
    }

    override fun onClick(clickedButton: View?) {
        when(clickedButton){
            trueButton -> {sharedViewModel.onAnswered(true,this)}
            falseButton ->{sharedViewModel.onAnswered(false,this)}

        }
    }

    override fun onCompleteTest() {
        sharedViewModel.setupResultFragment(fragmentManager!!)
    }
}