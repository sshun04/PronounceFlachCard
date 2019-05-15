package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.domain.CardRecyclerViewAdapterUseCase

class CardRecyclerAdapterViewModel(private val onDataChangedListener: OnDataChangedListener):ViewModel() {


   private val cardRecyclerAdapaterUsecase   = CardRecyclerViewAdapterUseCase (onDataChangedListener)

    fun editWord(id:String,word:String,meaning:String){
        cardRecyclerAdapaterUsecase.editWord(id,word,meaning)
    }

    fun  deleteWord(id: String){
        cardRecyclerAdapaterUsecase.deleteWord(id)
    }


//    できればCustomDialogFragmentに任せたい
//    RecyclerViewAdapterはFragmentManagerを持っていないから難し

//    fun setUpEditWordDialog(childFragmentManager: FragmentManager,wordId: String){
//        val editDialog = EditWordDialogFragment.newInstance(onDataChangedListener,wordId)
//        editDialog.show(childFragmentManager,"edit_dialog")
//    }
//
//    fun setUpDeleteDialog(childFragmentManager: FragmentManager,wordId: String){
//
//        val deleteDialog = DeleteWordDialogFragment.newInstance(onDataChangedListener,wordId)
//        deleteDialog.show(childFragmentManager,"delete_dialog")
//
//    }


}