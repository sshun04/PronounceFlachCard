package com.shojishunsuke.pronounceflachcard.testFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.WordObject
import io.realm.Realm
import io.realm.RealmResults


class TestMeaningShowWordFragment:Fragment() {

    lateinit var showingCards :RealmResults<WordObject>
    lateinit var showingWord : String
    val realm =Realm.getDefaultInstance()

    var trueNumbersList = arrayListOf<Int>()
    var falseNumbersList = arrayListOf<Int>()




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout  = inflater.inflate(R.layout.fragment_test_meaning_show_word,container,false)

        val textView = layout.findViewById<TextView>(R.id.meaningTextView3)
        val key_checked = resources.getString(R.string.key_is_checked_Only)
        val key_question_number = resources.getString(R.string.key_question_number)
        val key_true_numbers =  resources.getString(R.string.key_true_numbers)
        val key_false_numbers =resources.getString(R.string.key_false_numbers)

        val questionNumber = arguments!!.getInt(key_question_number)
        val isCheckedOnly =  arguments!!.getBoolean(key_checked)
        trueNumbersList = arguments!!.getIntegerArrayList(key_true_numbers)
        falseNumbersList = arguments!!.getIntegerArrayList(key_false_numbers)

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
        bundle.putIntegerArrayList(key_true_numbers,trueNumbersList)
        bundle.putIntegerArrayList(key_false_numbers,falseNumbersList)



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