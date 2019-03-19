package com.shojishunsuke.pronounceflachcard.testFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.WordObject
import io.realm.Realm
import io.realm.RealmResults

class TestMeaningShowAnswerFragment : Fragment() {

    val realm = Realm.getDefaultInstance()
    lateinit var showingCards: RealmResults<WordObject>
    lateinit var showingMeaning: String
    lateinit var textView: TextView
    lateinit var trueButton: Button
    lateinit var falseButton: Button

    var key_checked = ""
    var key_question_number = ""
    var key_true_numbers = ""
    var key_false_numbers = ""

    var questionNumber = 0
    var isCheckedOnly = true
    var trueNumbersList = arrayListOf<Int>()
    var falseNumbersList = arrayListOf<Int>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_test_meaning_show_answer, container, false)

        textView = layout.findViewById(R.id.textView2)
        trueButton = layout.findViewById(R.id.trueButton)
        falseButton = layout.findViewById(R.id.falseButton)

        key_checked = resources.getString(R.string.key_is_checked_Only)
        key_question_number = resources.getString(R.string.key_question_number)
        key_true_numbers = resources.getString(R.string.key_true_numbers)
        key_false_numbers = resources.getString(R.string.key_false_numbers)

        questionNumber = arguments!!.getInt(key_question_number)
        isCheckedOnly = arguments!!.getBoolean(key_checked)
        trueNumbersList = arguments!!.getIntegerArrayList(key_true_numbers)
        falseNumbersList = arguments!!.getIntegerArrayList(key_false_numbers)

        if (isCheckedOnly) {

            showingCards = realm.where(WordObject::class.java).equalTo("isDone", true).findAll()
            showingMeaning = showingCards.get(questionNumber)!!.meaning
        } else {

            showingCards = realm.where(WordObject::class.java).findAll()
            showingMeaning = showingCards.get(questionNumber)!!.meaning

        }

        textView.setText(showingMeaning + "/" + questionNumber.toString())

        trueButton.setOnClickListener {

         //正解した問題の番号を保存
            trueNumbersList.add(questionNumber)


            if (showingCards.count() == questionNumber + 1) {

                moveToResultFragment()
            } else {
               showWordFragment()
            }


        }

        falseButton.setOnClickListener {

            falseNumbersList.add(questionNumber)



            if (showingCards.count() == questionNumber + 1) {

                moveToResultFragment()

            } else {
                showWordFragment()
            }

        }





        return layout
    }

    fun moveToResultFragment() {

        val fragmentTransAction = fragmentManager!!.beginTransaction()
//        fragmentTransAction.addToBackStack(null)

        val showResultFragment = TestResultFragment()
        var bundle = Bundle()
        bundle.putIntegerArrayList(key_true_numbers, trueNumbersList)
        bundle.putIntegerArrayList(key_false_numbers, falseNumbersList)

        showResultFragment.arguments = bundle

        fragmentTransAction.replace(R.id.testMeaningBackGround, showResultFragment)
        fragmentTransAction.commit()

    }

    fun showWordFragment() {

        val fragmentTransAction = fragmentManager!!.beginTransaction()
//        fragmentTransAction.addToBackStack(null)

        var bundle = Bundle()
        questionNumber++
        bundle.putInt(key_question_number, questionNumber)
        bundle.putBoolean(key_checked, isCheckedOnly)
        bundle.putIntegerArrayList(key_true_numbers, trueNumbersList)
        bundle.putIntegerArrayList(key_false_numbers, falseNumbersList)

        val showWordFragment = TestMeaningShowWordFragment()
        showWordFragment.arguments = bundle

        fragmentTransAction.replace(R.id.testMeaningBackGround, showWordFragment)
        fragmentTransAction.commit()
    }
}