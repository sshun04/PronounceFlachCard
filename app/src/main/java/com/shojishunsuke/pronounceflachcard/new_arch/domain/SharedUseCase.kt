package com.shojishunsuke.pronounceflachcard.new_arch.domain

import com.shojishunsuke.pronounceflachcard.new_arch.data.SharedPrefRepository

class SharedUseCase(private val sharedPrefRepository: SharedPrefRepository){
    fun updateCurrentLabel(title:String){
        sharedPrefRepository.updateCurrentLabel(title)
    }
    fun getLatestLabel():String{
        return sharedPrefRepository.getLatestLabel()
    }

}