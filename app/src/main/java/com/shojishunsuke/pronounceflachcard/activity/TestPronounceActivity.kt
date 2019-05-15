package com.shojishunsuke.pronounceflachcard.activity

import android.content.DialogInterface
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.KeyEvent
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.testFragment.TestPronounceFragment


class TestPronounceActivity : AppCompatActivity() {




    private var questionNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_pronounce)

       val key_question_words = resources.getString(R.string.key_question_words)
        val key_question_number = resources.getString(R.string.key_question_number)

        val toolbar = this.findViewById<Toolbar>(R.id.testPronounceToolBar)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setTitle("発音テスト")

        val questionWords = intent.getSerializableExtra(key_question_words)as ArrayList<QuestionWord>





        val bundle = Bundle()

        bundle.putInt(key_question_number,questionNumber)
        bundle.putSerializable(key_question_words,questionWords)

        val testPronounceFragment = TestPronounceFragment()
        testPronounceFragment.arguments = bundle

        val fragmentTransAction = supportFragmentManager.beginTransaction()
        fragmentTransAction.addToBackStack(null)
        fragmentTransAction.replace(R.id.testPronounceBackground, testPronounceFragment)
        fragmentTransAction.commit()


    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        if (event!!.keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder(this)
                .setMessage("テストを終了しますか？")
                .setPositiveButton("終了", DialogInterface.OnClickListener { _, _ ->
                    finish()
                })
                .setNegativeButton("キャンセル", DialogInterface.OnClickListener { dialogInterface, _ ->
                    dialogInterface.dismiss()
                })
                .show()
        }
        return super.dispatchKeyEvent(event)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
       when(item?.itemId){
           android.R.id.home ->{
               AlertDialog.Builder(this)
                   .setMessage("テストを終了しますか？")
                   .setPositiveButton("終了", DialogInterface.OnClickListener { _, _ ->
                       finish()
                   })
                   .setNegativeButton("キャンセル", DialogInterface.OnClickListener { dialogInterface, _ ->
                       dialogInterface.dismiss()
                   })
                   .show()

               return true
           }
       }

        return false
    }
}
