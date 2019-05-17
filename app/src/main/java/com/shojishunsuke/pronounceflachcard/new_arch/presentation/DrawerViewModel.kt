package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.EditText
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.domain.DrawerUseCase

class DrawerViewModel(onDataChangedListener: OnDataChangedListener) {

    private val useCase = DrawerUseCase(onDataChangedListener)

    lateinit var registerEditText: EditText

    fun loadTitleList(): List<String> {

        return useCase.loadWholeTitleList()

    }

    private fun registerNewTitle(title: String) {

        return useCase.regitsterNewListTitle(title)

    }

    fun popupRegisterDialogFragment(context: Context) {


        val registerDialog = AlertDialog.Builder(context)
            .setPositiveButton("登録", DialogInterface.OnClickListener { _, _ ->

                registerNewTitle(registerEditText.text.toString())
            })
            .setNegativeButton(
                "キャンセル",
                null
            )
            .create()

        val parentView = registerDialog.layoutInflater.inflate(R.layout.dialog_fragment_register_title, null)

        registerEditText = parentView.findViewById(R.id.registerEditText)

        registerDialog.setView(parentView)
        registerDialog.show()

    }
}