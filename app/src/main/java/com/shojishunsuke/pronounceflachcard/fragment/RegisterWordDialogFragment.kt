package com.shojishunsuke.pronounceflachcard.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.RegisterWordDialogViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.SharedViewModel

class RegisterWordDialogFragment : DialogFragment() {


    lateinit var registerWordDialogViewModel: RegisterWordDialogViewModel


    companion object {
        fun newInstance(onDataChangedListener: OnDataChangedListener): RegisterWordDialogFragment {
            val dialog = RegisterWordDialogFragment()

            dialog.registerWordDialogViewModel = RegisterWordDialogViewModel(onDataChangedListener)
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = Dialog(requireContext())

        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
        )

        try {

            dialog.setContentView(R.layout.dialog_fragment_add_word)

        } catch (e: Exception) {
            e.stackTrace
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val wordEditText = dialog.findViewById<EditText>(R.id.wordEditText)
        val meaningEditText = dialog.findViewById<EditText>(R.id.meaningEditText)


        dialog.findViewById<Button>(R.id.positiveButton).setOnClickListener {

            val sharedViewModel = requireActivity().run {
                ViewModelProviders.of(this).get(SharedViewModel::class.java)
            }
            registerWordDialogViewModel.registerWord(
                wordEditText.text.toString(),
                meaningEditText.text.toString(),
                sharedViewModel.title
            )

            dismiss()

        }

        dialog.findViewById<Button>(R.id.negativeButton).setOnClickListener {
            dismiss()
        }

        return dialog

    }


}