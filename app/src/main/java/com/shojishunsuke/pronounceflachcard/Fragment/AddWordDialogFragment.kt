package com.shojishunsuke.pronounceflachcard.Fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.WordObject
import io.realm.Realm
import kotlinx.android.synthetic.main.dialog_fragment_add_word.*
import java.lang.Exception

class AddWordDialogFragment : DialogFragment() {

    val realm: Realm = Realm.getDefaultInstance()
    lateinit var wordEditText: EditText
    lateinit var meaningEditText: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var dialog = Dialog(activity)

        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)

        try {

            dialog.setContentView(R.layout.dialog_fragment_add_word)


        }catch (e :Exception){
            e.stackTrace
        }

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        wordEditText = dialog.findViewById(R.id.wordEditText)
        meaningEditText = dialog.findViewById(R.id.meaningEditText)


        dialog.findViewById<Button>(R.id.positiveButton).setOnClickListener(View.OnClickListener {

           addWord(wordEditText.text.toString(),meaningEditText.text.toString())
            dismiss()

        })

        dialog.findViewById<Button>(R.id.negativeButton).setOnClickListener(View.OnClickListener {

            dismiss()

        })

        return dialog

    }

    fun addWord(editWord: String, editMeaning: String) {

        realm.executeTransaction {

            var wordCard = it.createObject(WordObject::class.java)

            wordCard.word = editWord
            wordCard.meaning = editMeaning

            it.copyToRealm(wordCard)
        }
    }
}