package com.shojishunsuke.pronounceflachcard.testFragment

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.graphics.drawable.RippleDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.shape.CutCornerTreatment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapePathModel
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.activity.TestMeaningActivity
import com.shojishunsuke.pronounceflachcard.activity.TestPronounceActivity
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_test_tab.*

class TestFragment : Fragment() {

    val realm = Realm.getDefaultInstance()

    lateinit var checkedWords: RealmResults<WordObject>
    lateinit var testPronounceButton: MaterialButton
    lateinit var testMeaningButton: Button
    lateinit var checkedWordsButton:MaterialButton
    lateinit var allWordsButton: Button
    lateinit var floatingBackButton :FloatingActionButton

    var isPronounce = false
    var isCheckedWords = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_test_tab, container, false)

        testPronounceButton = layout.findViewById<MaterialButton>(R.id.pronounceTestButton).apply {

            setSharpCutButton(this)
            elevation = 8f
        }
        testMeaningButton = layout.findViewById(R.id.meaningTestButton)
        checkedWordsButton = layout.findViewById<MaterialButton>(R.id.checkedWordsButton).apply {

            setSharpCutButton(this)
            elevation = 8f
        }
        allWordsButton = layout.findViewById(R.id.allWordsButton)
        floatingBackButton = layout.findViewById(R.id.testBackButton)



        floatingBackButton.setImageResource(R.drawable.baseline_refresh_white_18dp)


        floatingBackButton.setOnClickListener {
            onResume()
        }

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
        floatingBackButton.hide()

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
        floatingBackButton.show()
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

    private fun setSharpCutButton(materialButton: MaterialButton){
        val shapePathModel = ShapePathModel().apply {
            topLeftCorner = CutCornerTreatment(48f)
            bottomRightCorner = CutCornerTreatment(48f)

        }

        val shapeDrawable = MaterialShapeDrawable(shapePathModel).apply {
            setTint(ContextCompat.getColor(context!!,R.color.colorCardBack))
        }

        materialButton.background = shapeDrawable.toRipple()

    }

    private fun Drawable.toRipple():RippleDrawable =
            RippleDrawable(ContextCompat.getColorStateList(context!!,R.color.mtrl_btn_ripple_color),
                this,
                null)

}