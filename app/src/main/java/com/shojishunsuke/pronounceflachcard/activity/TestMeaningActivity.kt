package com.shojishunsuke.pronounceflachcard.activity

import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.testFragment.TestMeaningShowWordFragment


class TestMeaningActivity : AppCompatActivity() {

    var isCheckedOnly = true
    var questionNumber = 0

    lateinit var toolbar: Toolbar
    val questionWordsList = ArrayList<QuestionWord>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_meaning)
        val key_checked = resources.getString(R.string.key_is_checked_Only)
        val key_question_number = resources.getString(R.string.key_question_number)
        val key_quesiton_words = resources.getString(R.string.key_question_words)

        toolbar = this.findViewById(R.id.testMeaningToolBar)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setTitle("意味テスト")


        isCheckedOnly = intent.getBooleanExtra(key_checked, true)

        var bundle = Bundle()
        bundle.putInt(key_question_number, questionNumber)
        bundle.putBoolean(key_checked, isCheckedOnly)
        bundle.putSerializable(key_quesiton_words, questionWordsList)


        if (savedInstanceState == null) {

            val showWordFragment = TestMeaningShowWordFragment()
            showWordFragment.arguments = bundle


            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.replace(
                R.id.testMeaningBackGround,
                showWordFragment
            )
            fragmentTransaction.commit()
        }
    }

    fun showConfirmDialog() {

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

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        if (event!!.keyCode == KeyEvent.KEYCODE_BACK) {
            showConfirmDialog()
        }
        return super.dispatchKeyEvent(event)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                showConfirmDialog()
                return true
            }
            else -> {
                return false
            }

        }

    }
}
