package com.shojishunsuke.pronounceflachcard.Fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.shojishunsuke.pronounceflachcard.R
import io.realm.Realm
import kotlinx.android.synthetic.main.dialog_fragment_edit_word.*
import java.lang.Exception

class EditWordDialogFragment:DialogFragment() {

    val realm:Realm = Realm.getDefaultInstance()

    lateinit var  wordEditText: EditText
    lateinit var  meaningEditText: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var dialog = Dialog(activity)

        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)

        try {
            dialog.setContentView(R.layout.dialog_fragment_edit_word)
        }catch (e:Exception){
            e.stackTrace
        }

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        wordEditText = dialog.findViewById(R.id.wordEditText)
        meaningEditText = dialog.findViewById(R.id.meaningEditText)


        return super.onCreateDialog(savedInstanceState)
    }
}