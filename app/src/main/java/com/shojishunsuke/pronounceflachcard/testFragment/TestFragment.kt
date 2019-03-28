package com.shojishunsuke.pronounceflachcard.testFragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.activity.TestMeaningActivity
import com.shojishunsuke.pronounceflachcard.activity.TestPronounceActivity
import com.shojishunsuke.pronounceflachcard.activity.realm
import io.realm.RealmResults

class TestFragment : Fragment() {

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


            if (realm.where(WordObject::class.java).equalTo("isDone", true).findAll().isEmpty()) {
//               チェックされた単語が０個ならダイアログ表示

                showSimpleAlertDialog("テストを開始するには単語にチェックをつけてください。")

            } else {
                isCheckedWords = true
                openWordTestFragment()
            }

        }

        allWordsButton.setOnClickListener {

            if (realm.where(WordObject::class.java).findAll().isEmpty()) {
//                単語が０個ならダイアログ表示

                showSimpleAlertDialog("テストを開始するには単語を追加してください。")

            } else {
                isCheckedWords = false
                openWordTestFragment()
            }

        }

        realm.addChangeListener {

            checkedWords = realm.where(WordObject::class.java).equalTo("isDone", true).findAll()

        }

        return layout
    }

    override fun onResume() {
        super.onResume()

        testPronounceButton.visibility = View.VISIBLE
        testMeaningButton.visibility = View.VISIBLE

        checkedWordsButton.visibility = View.GONE
        allWordsButton.visibility = View.GONE
    }

    private fun showSimpleAlertDialog(message: String) {

        AlertDialog.Builder(context)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()

    }

    private fun hideFirstButtons() {

        testPronounceButton.visibility = View.GONE
        testMeaningButton.visibility = View.GONE

    }

    private fun showSecondButtons() {

        checkedWordsButton.visibility = View.VISIBLE
        allWordsButton.visibility = View.VISIBLE

    }

    private fun openWordTestFragment() {
        when (isPronounce) {
            true -> {

                val intent = Intent(context, TestPronounceActivity::class.java)
                intent.putExtra(resources.getString(R.string.key_is_checked_Only), isCheckedWords)

                context!!.startActivity(intent)

            }

            false -> {

                val intent = Intent(context, TestMeaningActivity::class.java)
                intent.putExtra(resources.getString(R.string.key_is_checked_Only), isCheckedWords)

                context!!.startActivity(intent)

            }
        }
    }


}