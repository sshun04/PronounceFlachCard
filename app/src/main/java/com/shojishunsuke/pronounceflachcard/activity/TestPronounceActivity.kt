package com.shojishunsuke.pronounceflachcard.activity

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shojishunsuke.pronounceflachcard.R

lateinit var speechRecognizer: SpeechRecognizer

class TestPronounceActivity : AppCompatActivity() {

    private var speechText: String = ""
    lateinit var textView: TextView
    private lateinit var button: Button
    private var i  = 0
    private var isFirst = true
    private lateinit var recognitionListener: RecognitionListener



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_pronounce)


        textView = this.findViewById(R.id.resultTextView1)
        button = this.findViewById(R.id.button1)


        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.packageName)

        button.setOnClickListener {

            if (isFirst){
                isFirst = false
            }else{
                i++
            }
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
            speechRecognizer.setRecognitionListener(recognitionListener)

            speechRecognizer.startListening(intent)

        }

        recognitionListener = object : RecognitionListener{

            override fun onReadyForSpeech(p0: Bundle?) {

                Log.d("PronounceActivity", "ready")

            }

            override fun onRmsChanged(p0: Float) {

            }

            override fun onBufferReceived(p0: ByteArray?) {

            }

            override fun onPartialResults(p0: Bundle?) {

            }

            override fun onEvent(p0: Int, p1: Bundle?) {

            }

            override fun onBeginningOfSpeech() {

            }

            override fun onEndOfSpeech() {

                Log.d("PronounceActivity", "speech end")

            }

            override fun onError(p0: Int) {

                Log.d("PronounceActivity","error")

            }

            override fun onResults(p0: Bundle?) {
                Log.d("PronounceActivity", "onResult")

                val key = SpeechRecognizer.RESULTS_RECOGNITION
                val stringList = p0!!.getStringArrayList(key)
                if (p0 != null) {
                    speechText = stringList.get(i)
                    textView.setText(speechText)

                    speechRecognizer.destroy()

                } else {

                    Toast.makeText(applicationContext, "もっとちゃんと話して", Toast.LENGTH_SHORT).show()

                }
            }

        }

    }
}
