package com.shojishunsuke.pronounceflachcard.activity

import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.new_arch.factory.TestSharedViewModelFactory
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.TestSharedViewModel
import com.shojishunsuke.pronounceflachcard.testFragment.TestMeaningShowWordFragment


class TestMeaningActivity : AppCompatActivity() {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_meaning)


        val toolbar = this.findViewById<Toolbar>(R.id.testMeaningToolBar)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setTitle("意味テスト")

        val questionWords = intent.getSerializableExtra("list") as ArrayList<QuestionWord>



        val testSharedViewModel = ViewModelProviders.of(this,
            TestSharedViewModelFactory(questionWords)
        ).get(TestSharedViewModel::class.java)

        val testMeaningShowWordFragment = TestMeaningShowWordFragment()

        val fragmentTransAction = supportFragmentManager.beginTransaction()
        fragmentTransAction.addToBackStack(null)
        fragmentTransAction.replace(R.id.testMeaningBackGround,testMeaningShowWordFragment)
        fragmentTransAction.commit()




    }



    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        if (event!!.keyCode == KeyEvent.KEYCODE_BACK) {
            showConfirmDialog()
        }
        return super.dispatchKeyEvent(event)
    }

    private fun showConfirmDialog() {

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
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                showConfirmDialog()
                true
            }
            else -> false

        }

    }
}
