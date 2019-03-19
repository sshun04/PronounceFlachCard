package com.shojishunsuke.pronounceflachcard.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.WordObject
import io.realm.Realm
import io.realm.RealmResults

class TestFragment : Fragment() {

    val realm = Realm.getDefaultInstance()
    lateinit var checkedWords: RealmResults<WordObject>
    lateinit var testPronounceButton: Button
    lateinit var testMeaningButton: Button
    lateinit var checkedWordsButton: Button
    lateinit var allWordsButton: Button

    var isPronounce = false
    var isCheckedWords = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_test_tab, container, false)

        testPronounceButton = layout.findViewById(R.id.pronounceTestButton)
        testMeaningButton = layout.findViewById(R.id.meaningTestButton)
        checkedWordsButton = layout.findViewById(R.id.checkedWordsButton)
        allWordsButton = layout.findViewById(R.id.allWordsButton)

        testPronounceButton.setOnClickListener {
            isPronounce = true
            hideFirstButtons()
            showSecondButtons()
        }

        testMeaningButton.setOnClickListener {
            isPronounce = false
            hideFirstButtons()
            showSecondButtons()
        }

        checkedWordsButton.setOnClickListener {
            isCheckedWords = true
            openTestFragment()
            Log.d("words", isCheckedWords.toString() + ":" + isPronounce.toString())
        }


        allWordsButton.setOnClickListener {
            isCheckedWords = false

            openTestFragment()
            Log.d("words", isCheckedWords.toString() + ":" + isPronounce.toString())

        }

        realm.addChangeListener {

            checkedWords = realm.where(WordObject::class.java).equalTo("isDone", true).findAll()

            Log.d("a", checkedWords.count().toString())

        }

        return layout
    }

    fun hideFirstButtons() {
        testPronounceButton.visibility = View.GONE
        testMeaningButton.visibility = View.GONE
    }

    fun showSecondButtons() {

        checkedWordsButton.visibility = View.VISIBLE
        allWordsButton.visibility = View.VISIBLE

    }

    fun openTestFragment() {
        when (isPronounce ) {
            true -> {

                var fragmentTransAction: FragmentTransaction = fragmentManager!!.beginTransaction()
                fragmentTransAction.replace(R.id.testFragmentBackground, createTestFragment())

                fragmentTransAction.commit()

            }

            false->{

                var fragmentTransaction = fragmentManager!!.beginTransaction()
                fragmentTransaction.replace(R.id.testFragmentBackground,createTestFragment())

                fragmentTransaction.commit()

            }
        }
    }

    fun createTestPronounceFragment(): TestPronounceFragment {
        var bundle = Bundle()
        bundle.putBoolean("isChecked", isCheckedWords)

        val testPronounceFragment = TestPronounceFragment()
        testPronounceFragment.arguments = bundle

        return testPronounceFragment
    }

    fun createTestFragment():Fragment{

        var bundle = Bundle()
        bundle.putBoolean("isChecked", isCheckedWords)


        if (isPronounce){

            val testPronounceFragment = TestPronounceFragment()
            testPronounceFragment.arguments = bundle

            return testPronounceFragment

        }else{

            val testMeaningFragment = TestMeaningFragment()
            testMeaningFragment.arguments = bundle

            return testMeaningFragment


        }



    }

}