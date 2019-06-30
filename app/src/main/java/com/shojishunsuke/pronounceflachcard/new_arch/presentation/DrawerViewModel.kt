package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.EditText
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.Model.FlashCardTitle
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.adapter.DrawerRecyclerViewAdapter
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.domain.DrawerUsecase
import io.realm.RealmResults

class DrawerViewModel : ViewModel(), OnDataChangedListener {

    private val useCase = DrawerUsecase(this)

    lateinit var registerEditText: EditText

    val currentAdapter: MutableLiveData<DrawerRecyclerViewAdapter> = MutableLiveData()

    fun loadTitleList(): RealmResults<FlashCardTitle> {
        return useCase.loadWholeTitleList()
    }

    private fun registerNewTitle(title: String) {

        return useCase.registerNewListTitle(title)

    }

    fun popupRegisterDialogFragment(context: Context, fragmentActivity: FragmentActivity) {


        val registerDialog = AlertDialog.Builder(context)
            .setPositiveButton("登録", DialogInterface.OnClickListener { _, _ ->
                registerNewTitle(registerEditText.text.toString())
                updateRecyclerView(context, fragmentActivity)
            })
            .setNegativeButton(
                "キャンセル",
                null
            ).create()


        val parentView = registerDialog.layoutInflater.inflate(R.layout.dialog_fragment_register_title, null)
        registerEditText = parentView.findViewById(R.id.registerEditText)

        registerDialog.setView(parentView)
        registerDialog.show()

    }

    fun updateRecyclerView(context: Context, activity: FragmentActivity) {
        val adapter = DrawerRecyclerViewAdapter(activity, context, loadTitleList())
        currentAdapter.value = adapter
    }

    fun deleteList(title: String) {
        useCase.deleteListTitle(title)
    }

    override fun onDataChanged() {

    }
}