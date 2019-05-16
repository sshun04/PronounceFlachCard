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
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.DeleteWordDialogViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.EditDialogViewModel
import java.lang.Exception

class DeleteWordDialogFragment :DialogFragment(){
    private lateinit var viewModel:DeleteWordDialogViewModel

    companion object {

        fun newInstance(onDataChangedListener: OnDataChangedListener, wordId:String): DeleteWordDialogFragment {
            val dialog = DeleteWordDialogFragment()
            val bundle = Bundle()
            bundle.putString("id",wordId)
            dialog.arguments  = bundle
            dialog.viewModel = DeleteWordDialogViewModel(onDataChangedListener)
            return dialog
        }
    }
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




        dialog.findViewById<Button>(R.id.positiveButton).setOnClickListener(View.OnClickListener {



            viewModel.deleteWord(id!!)

            dismiss()

        })

        dialog.findViewById<Button>(R.id.negativeButton).setOnClickListener(View.OnClickListener {

            dismiss()

        })

        return dialog
    }

}