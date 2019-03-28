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
import com.shojishunsuke.pronounceflachcard.activity.realm
import io.realm.RealmResults

class TestMeaningShowAnswerFragment : Fragment() {


    lateinit var showingCards: RealmResults<WordObject>
    lateinit var showingMeaning: String
    lateinit var shownWord: String
    lateinit var textView: TextView
    lateinit var trueButton: Button
    lateinit var falseButton: Button

    lateinit var key_checked: String
    lateinit var key_question_number: String
    lateinit var key_quesiton_words: String

    lateinit var questionWordsList: ArrayList<QuestionWord>


    var questionNumber = 0
    var isCheckedOnly = true


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_test_meaning_show_answer, container, false)

        textView = layout.findViewById(R.id.textView2)
        trueButton = layout.findViewById(R.id.trueButton)
        falseButton = layout.findViewById(R.id.falseButton)

        key_checked = resources.getString(R.string.key_is_checked_Only)
        key_question_number = resources.getString(R.string.key_question_number)
        key_quesiton_words = resources.getString(R.string.key_question_words)


        questionNumber = arguments!!.getInt(key_question_number)
        isCheckedOnly = arguments!!.getBoolean(key_checked)

        questionWordsList = arguments!!.getSerializable(key_quesiton_words) as ArrayList<QuestionWord>

        if (isCheckedOnly) {

            showingCards = realm.where(WordObject::class.java).equalTo("isDone", true).findAll()

        } else {

            showingCards = realm.where(WordObject::class.java).findAll()

        }

        showingMeaning = showingCards.get(questionNumber)!!.meaning
        shownWord = showingCards.get(questionNumber)!!.word

        textView.setText(showingMeaning)

        val questionWord = QuestionWord()
        questionWord.quetionNumber = questionNumber
        questionWord.woord = shownWord
        questionWord.isPronounce =false

        trueButton.setOnClickListener {

            //正解した問題の番号を保存
            questionWord.isTrue = true



            if (showingCards.count() == questionNumber + 1) {

                questionWordsList.add(questionWord)

                showResultFragment()

            } else {
                questionWordsList.add(questionWord)

                showWordFragment()

            }
        }

        falseButton.setOnClickListener {

            questionWord.isTrue = false

            if (showingCards.count() == questionNumber + 1) {


                questionWordsList.add(questionWord)



                showResultFragment()

            } else {
                showWordFragment()
            }

        }

        return layout
    }

    private fun showResultFragment() {

        val fragmentTransAction = fragmentManager!!.beginTransaction()

        val showResultFragment = TestResultFragment()

        var bundle = Bundle()

        bundle.putBoolean(key_checked, isCheckedOnly)
        bundle.putSerializable(key_quesiton_words, questionWordsList)

        showResultFragment.arguments = bundle

        fragmentTransAction.replace(R.id.testMeaningBackGround, showResultFragment)
        fragmentTransAction.commit()

    }

    private fun showWordFragment() {

        val fragmentTransAction = fragmentManager!!.beginTransaction()

        var bundle = Bundle()
        questionNumber++

        bundle.putInt(key_question_number, questionNumber)
        bundle.putBoolean(key_checked, isCheckedOnly)
        bundle.putSerializable(key_quesiton_words, questionWordsList)

        val showWordFragment = TestMeaningShowWordFragment()
        showWordFragment.arguments = bundle

        fragmentTransAction.replace(R.id.testMeaningBackGround, showWordFragment)
        fragmentTransAction.commit()
    }
}