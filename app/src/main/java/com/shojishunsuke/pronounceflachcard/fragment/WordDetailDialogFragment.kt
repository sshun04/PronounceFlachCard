package com.shojishunsuke.pronounceflachcard.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.shojishunsuke.pronounceflachcard.R
import io.realm.Realm
import java.lang.Exception

class WordDetailDialogFragment:DialogFragment() {

    lateinit var detailWordTextView: TextView
    lateinit var detailMeaningTextView: TextView



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var dialog = Dialog(activity)

        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)


        try {
            dialog.setContentView(R.layout.dialog_fragment_word_detail)
        }catch (e:Exception){
            e.stackTrace
        }


        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        detailWordTextView = dialog.findViewById(R.id.wordEditText)
        detailMeaningTextView = dialog.findViewById(R.id.meaningEditText)



        return super.onCreateDialog(savedInstanceState)
    }
}