package com.shojishunsuke.pronounceflachcard.testFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.R
import io.realm.Realm
import io.realm.RealmResults

class TestMeaningShowAnswerFragment : Fragment(), View.OnClickListener {

    val realm = Realm.getDefaultInstance()



    lateinit var textView: TextView
    lateinit var trueButton: Button
    lateinit var falseButton: Button

    lateinit var key_question_number: String
    lateinit var key_quesiton_words: String

    lateinit var questionWord :QuestionWord

    lateinit var questionWordsList: ArrayList<QuestionWord>


     private var questionNumber:Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_test_meaning_show_answer, container, false)

        textView = layout.findViewById(R.id.textView2)
        trueButton = layout.findViewById(R.id.trueButton)
        falseButton = layout.findViewById(R.id.falseButton)

        key_question_number = resources.getString(R.string.key_question_number)
        key_quesiton_words = resources.getString(R.string.key_question_words)


        questionNumber = arguments!!.getInt(key_question_number)

        questionWordsList = arguments!!.getSerializable(key_quesiton_words) as ArrayList<QuestionWord>


        questionWord =questionWordsList[questionNumber]

        textView.setText(questionWord.meaning)


        trueButton.setOnClickListener(this)
        falseButton.setOnClickListener(this)


        return layout
    }




    override fun onClick(p0: View?) {
        when(p0){
            trueButton -> {questionWord.isTrue = true}
            falseButton ->{questionWord.isTrue = false }

        }

        if (questionWordsList.size == questionNumber+1 ) {


            showFragment(fragment =  TestResultFragment(),isResult = true)

        } else {


            showFragment(fragment = TestMeaningShowWordFragment(),isResult = false)
        }


    }
    private fun showFragment(fragment: Fragment, isResult: Boolean) {
        val bundle = Bundle()

        bundle.putSerializable(key_quesiton_words, questionWordsList)

        if (!isResult) {
            questionNumber++
            bundle.putInt(key_question_number, questionNumber)
        }

        fragment.arguments = bundle
        val fragmentTransAction = fragmentManager!!.beginTransaction()
        fragmentTransAction.replace(R.id.testMeaningBackGround,fragment)
        fragmentTransAction.commit()



    }
}