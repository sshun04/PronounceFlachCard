package com.shojishunsuke.pronounceflachcard.testFragment

import android.content.Intent
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
import com.shojishunsuke.pronounceflachcard.activity.TestMeaningActivity
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
//            hideFirstButtons()
//            showSecondButtons()

            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.replace(R.id.testFragmentBackground,createTestPronounceFragment())
            fragmentTransaction.commit()

        }

        testMeaningButton.setOnClickListener {
            isPronounce = false
            hideFirstButtons()
            showSecondButtons()
        }

        checkedWordsButton.setOnClickListener {
            isCheckedWords = true
            openTestFragment()

        }


        allWordsButton.setOnClickListener {
            isCheckedWords = false

            openTestFragment()

        }

        realm.addChangeListener {

            checkedWords = realm.where(WordObject::class.java).equalTo("isDone", true).findAll()

        }

        return layout
    }

    override fun onResume() {
        super.onResume()

        testPronounceButton.visibility =View.VISIBLE
        testMeaningButton.visibility =View.VISIBLE

        checkedWordsButton.visibility = View.GONE
        allWordsButton.visibility    =View.GONE
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

               val intent = Intent(context,TestMeaningActivity::class.java)
                intent.putExtra(resources.getString(R.string.key_is_checked_Only),isCheckedWords)

                context!!.startActivity(intent)

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

            val testMeaningFragment = TestMeaningShowWordFragment()
            testMeaningFragment.arguments = bundle

            return testMeaningFragment


        }



    }

}