package com.shojishunsuke.pronounceflachcard.testFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.activity.realm
import io.realm.RealmResults


class TestMeaningShowWordFragment:Fragment() {

    private lateinit var showingCards :RealmResults<WordObject>
    private lateinit var showingWord : String





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout  = inflater.inflate(R.layout.fragment_test_meaning_show_word,container,false)

        val textView = layout.findViewById<TextView>(R.id.meaningTextView3)
        val key_checked = resources.getString(R.string.key_is_checked_Only)
        val key_question_number = resources.getString(R.string.key_question_number)
        val key_quesiton_words = resources.getString(R.string.key_question_words)


        val questionNumber = arguments!!.getInt(key_question_number)
        val isCheckedOnly =  arguments!!.getBoolean(key_checked)
        val questionWordsList = arguments!!.getSerializable(key_quesiton_words)as ArrayList<QuestionWord>

        if (isCheckedOnly){
            showingCards = realm.where(WordObject::class.java).equalTo("isDone",true).findAll()
            showingWord = showingCards.get(questionNumber)!!.word
        }else{
            showingCards = realm.where(WordObject::class.java).findAll()
            showingWord = showingCards.get(questionNumber)!!.word
        }

        textView.setText(showingWord)

        var bundle = Bundle()
        bundle.putInt(key_question_number,questionNumber)
        bundle.putBoolean(key_checked,isCheckedOnly)
        bundle.putSerializable(key_quesiton_words,questionWordsList)



        textView.setOnClickListener {
            val showAnswerFragment = TestMeaningShowAnswerFragment()
            showAnswerFragment.arguments = bundle

            val fragmentTransAction = fragmentManager!!.beginTransaction()
            fragmentTransAction.replace(R.id.testMeaningBackGround,showAnswerFragment)
            fragmentTransAction.commit()


        }



        return layout
    }
}