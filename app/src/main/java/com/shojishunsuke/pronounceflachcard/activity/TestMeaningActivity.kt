package com.shojishunsuke.pronounceflachcard.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.WordObject
import com.shojishunsuke.pronounceflachcard.testFragment.TestMeaningShowWordFragment
import io.realm.Realm
import io.realm.RealmResults

class TestMeaningActivity : AppCompatActivity() {

    val realm = Realm.getDefaultInstance()
    var isCheckedOnly = true
    var questionNumber = 0
    var trueNumbersList = arrayListOf<Int>()
    var falseNumbersList = arrayListOf<Int>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_meaning)
        val key_checked = resources.getString(R.string.key_is_checked_Only)
        val key_question_number = resources.getString(R.string.key_question_number)
        val key_true_numbers =  resources.getString(R.string.key_true_numbers)
        val key_false_numbers =resources.getString(R.string.key_false_numbers)


        isCheckedOnly = intent.getBooleanExtra(key_checked, true)

//        Log.d("Log", R.string.key_is_checked_Only.toString())



        var bundle = Bundle()
        bundle.putInt(key_question_number,questionNumber)
        bundle.putBoolean(key_checked,isCheckedOnly)
        bundle.putIntegerArrayList(key_true_numbers,trueNumbersList)
        bundle.putIntegerArrayList(key_false_numbers,falseNumbersList)


        if (savedInstanceState == null) {

            val showWordFragment = TestMeaningShowWordFragment()
            showWordFragment.arguments = bundle


            var fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.replace(R.id.testMeaningBackGround,
               showWordFragment
            )
            fragmentTransaction.commit()




        }


    }


}
