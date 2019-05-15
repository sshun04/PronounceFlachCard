package com.shojishunsuke.pronounceflachcard.fragment

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
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.EditDialogViewModel
import java.lang.Exception

class EditWordDialogFragment :DialogFragment(){


    private lateinit var viewModel:EditDialogViewModel

//    companion object {
//
//        fun newInstance(onDataChangedListener: OnDataChangedListener,wordId:String): EditWordDialogFragment {
//            val editWordDialogFragment = EditWordDialogFragment()
//            val bundle = Bundle()
//            bundle.putString("id",wordId)
//            editWordDialogFragment.arguments  = bundle
//            editWordDialogFragment.viewModel = EditDialogViewModel(onDataChangedListener)
//            return editWordDialogFragment
//        }
//    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val id = arguments!!.getString("id")
        val dialog  = Dialog(context)

        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)

        try {

            dialog.setContentView(R.layout.dialog_fragment_add_word)

        }catch (e : Exception){
            e.stackTrace
        }

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

       val wordEditText = dialog.findViewById<EditText>(R.id.wordEditText)
       val meaningEditText = dialog.findViewById<EditText>(R.id.meaningEditText)


        dialog.findViewById<Button>(R.id.positiveButton).setOnClickListener(View.OnClickListener {



            viewModel.editWord(id!!,wordEditText.text.toString(),meaningEditText.text.toString())
            dismiss()

        })

        dialog.findViewById<Button>(R.id.negativeButton).setOnClickListener(View.OnClickListener {

            dismiss()

        })

        return dialog
    }


}