package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.fragment.RegisterWordDialogFragment
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.domain.FlashCardFragmentUsecase

class FlashCardFragmentViewModel(private val onDataChangedListener: OnDataChangedListener) :ViewModel(){



   private val flashCardFragmentUsecase = FlashCardFragmentUsecase(onDataChangedListener)

    fun getWordsList(listName :String):MutableList<WordObject>{
        return flashCardFragmentUsecase.provideWordList(listName)
    }

    fun setupAddWordDialogFragment(childFragmentManager: FragmentManager){
        val addDialog = RegisterWordDialogFragment.newInstance(onDataChangedListener)
        addDialog.show(childFragmentManager,"add_dialog")
    }
}