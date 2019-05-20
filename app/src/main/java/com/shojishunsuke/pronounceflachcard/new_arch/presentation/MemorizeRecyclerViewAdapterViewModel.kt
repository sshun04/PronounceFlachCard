package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.domain.MemorizeRecyclerViewAdapterUsecase
import java.util.*

class MemorizeRecyclerViewAdapterViewModel(onDataChangedListener: OnDataChangedListener,context: Context) :ViewModel(),TextToSpeech.OnInitListener{
   private val useCase = MemorizeRecyclerViewAdapterUsecase(onDataChangedListener)

    private val textToSpeech = TextToSpeech(context,this)

    fun switchCheckedState(id:String,isChecked:Boolean){
        useCase.switchCheckedState(id,isChecked)
    }

    fun readOut(){

    }

    override fun onInit(p0: Int) {
        if (p0 == TextToSpeech.SUCCESS){
            if (textToSpeech.isLanguageAvailable(Locale.ENGLISH)>= TextToSpeech.LANG_AVAILABLE) {
                textToSpeech.setLanguage(Locale.ENGLISH)
                textToSpeech.setSpeechRate(1.0f)
                textToSpeech.setPitch(1.0f)

            }else{
               Log.d("TextToSpeech.onInit","Failed")
            }
        }
    }
}