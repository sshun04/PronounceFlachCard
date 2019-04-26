package com.shojishunsuke.pronounceflachcard.activity

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.speech.RecognitionListener
import android.view.KeyEvent
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.testFragment.TestStartFragment


class TestPronounceActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_pronounce)

        val key_checked = resources.getString(R.string.key_is_checked_Only)

        val toolbar = this.findViewById<Toolbar>(R.id.testPronounceToolBar)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setTitle("発音テスト")




        val isCheckedOnly = intent.getBooleanExtra(key_checked, true)

        val bundle = Bundle()
        bundle.putBoolean(key_checked, isCheckedOnly)

        val testStartFragment = TestStartFragment()
        testStartFragment.arguments = bundle

        val fragmentTransAction = supportFragmentManager.beginTransaction()
        fragmentTransAction.addToBackStack(null)
        fragmentTransAction.replace(R.id.testPronounceBackground, testStartFragment)
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
