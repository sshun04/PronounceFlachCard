package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.fragment.AddWordDialogFragment
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.domain.FlashCardFragmentUseCase

class FlashCardFragmentViewModel(private val onDataChangedListener: OnDataChangedListener) :ViewModel(){



   private val flashCardFragmentUsecase = FlashCardFragmentUseCase(onDataChangedListener)

    fun getWordsList(listName :String):List<WordObject>{
        return flashCardFragmentUsecase.provideWordList(listName)
    }

    fun setupAddWordDialogFragment(childFragmentManager: FragmentManager){
        val addDialog = AddWordDialogFragment.newInstance(onDataChangedListener)
        addDialog.show(childFragmentManager,"add_dialog")
    }
}