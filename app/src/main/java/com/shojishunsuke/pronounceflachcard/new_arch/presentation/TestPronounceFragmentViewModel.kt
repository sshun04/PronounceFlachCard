package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.lifecycle.ViewModel

class TestPronounceFragmentViewModel : ViewModel() {

    fun setupSpeechRecognizer(context: Context, judgeListener: JudgeListener) {

        val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        speechIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.packageName)

        val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

        speechRecognizer.setRecognitionListener(
            object : RecognitionListener {

                override fun onReadyForSpeech(p0: Bundle?) {

                    Log.d("SpeechRecognizer", "ready")

                }

                override fun onRmsChanged(p0: Float) {}
                override fun onBufferReceived(p0: ByteArray?) {}
                override fun onPartialResults(p0: Bundle?) {}
                override fun onEvent(p0: Int, p1: Bundle?) {}
                override fun onBeginningOfSpeech() {}

                override fun onEndOfSpeech() {
                    Log.d("SpeechRecognizer", "speech end")
                }

                override fun onError(p0: Int) {
                    Log.d("SpeechRecognizer", "error")
                    speechRecognizer.destroy()
                }

                override fun onResults(result: Bundle?) {


                    if (result != null) {
                        val confidenceKey = SpeechRecognizer.CONFIDENCE_SCORES
                        val confidenceList = result.getFloatArray(confidenceKey)


                        val mostConfidentPosition = confidenceList.indexOfFirst {
                            it == confidenceList.max()
                        }

                        val wordkey = SpeechRecognizer.RESULTS_RECOGNITION
                        val recognitionResult = result.getStringArrayList(wordkey)[mostConfidentPosition]


                        // 一番信頼性の高い認識結果を用いて結果を判定する
                        // maybe-later 判定が厳しすぎるようならresult.contains(question)に変更する

                        judgeListener.onRecognized(recognitionResult)
                    } else {
                        Log.d("SpeechRecognizer", "Result == null")
                    }


                    speechRecognizer.destroy()
                }
            })

        speechRecognizer.startListening(speechIntent)

    }

    interface JudgeListener {
        fun onRecognized(recognitionResult: String)
    }


}