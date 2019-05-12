package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import androidx.fragment.app.FragmentManager
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.fragment.AddWordDialogFragment
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.domain.FlashCardFragmentUsecase

class FlashCardFragmentViewModel {

   private val flashCardFragmentUsecase = FlashCardFragmentUsecase()

    fun getList(listName :String):List<WordObject>{
        return flashCardFragmentUsecase.provideSortedList(listName)
    }

    fun showAddWordDialogFragment(childFragmentManager: FragmentManager){
        val addDialog = AddWordDialogFragment()
        addDialog.show(childFragmentManager,"add_dialog")
    }
}