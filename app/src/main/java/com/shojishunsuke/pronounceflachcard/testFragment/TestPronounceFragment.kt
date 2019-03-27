package com.shojishunsuke.pronounceflachcard.testFragment

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.WordObject
import com.shojishunsuke.pronounceflachcard.activity.realm
import io.realm.RealmResults

class TestPronounceFragment : Fragment() {

    var trueNumbersList = arrayListOf<Int>()
    var falseNumbersList = arrayListOf<Int>()

    var speechText = ""

    var recognizedWordsList = arrayListOf<String>()

    private lateinit var showingCards: RealmResults<WordObject>
    private lateinit var showingWord: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_test_pronounce, container, false)
        val textView = layout.findViewById<TextView>(R.id.wordTextView4)
        val nextButton = layout.findViewById<Button>(R.id.nextButton)
        val resultButton = layout.findViewById<Button>(R.id.resultButton)

        val key_checked = resources.getString(R.string.key_is_checked_Only)
        val key_question_number = resources.getString(R.string.key_question_number)
        val key_true_numbers = resources.getString(R.string.key_true_numbers)
        val key_false_numbers = resources.getString(R.string.key_false_numbers)

        val key_recognized_words = resources.getString(R.string.key_recognized_words)


        var questionNumber = arguments!!.getInt(key_question_number)
        val isCheckedOnly = arguments!!.getBoolean(key_checked)
        trueNumbersList = arguments!!.getIntegerArrayList(key_true_numbers)
        falseNumbersList = arguments!!.getIntegerArrayList(key_false_numbers)



        if (isCheckedOnly) {
            showingCards = realm.where(WordObject::class.java).equalTo("isDone", true).findAll()
            showingWord = showingCards.get(questionNumber)!!.word

        } else {
            showingCards = realm.where(WordObject::class.java).findAll()
            showingWord = showingCards.get(questionNumber)!!.word
        }

        textView.setText(showingWord)


        val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        speechIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context!!.packageName)

        val bundle = Bundle()

        val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

        speechRecognizer.setRecognitionListener(object : RecognitionListener {

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

                Log.d("PronounceActivity", "error")

            }

            override fun onResults(p0: Bundle?) {
                Log.d("PronounceActivity", "onResult")

                val key = SpeechRecognizer.RESULTS_RECOGNITION
                val stringList = p0!!.getStringArrayList(key)
                if (p0 != null) {
                    speechText = stringList.get(questionNumber)

                    if (showingWord == speechText) {

                        trueNumbersList.add(questionNumber)

                    } else {

                        falseNumbersList.add(questionNumber)
                    }

                    recognizedWordsList.add(speechText)


                    speechRecognizer.destroy()

                    if (showingCards.count() == questionNumber + 1) {


                        resultButton.visibility = View.VISIBLE


                    } else {

                        questionNumber++
                        nextButton.visibility = View.VISIBLE

                    }

                    bundle.putInt(key_question_number, questionNumber)
                    bundle.putBoolean(key_checked, isCheckedOnly)
                    bundle.putIntegerArrayList(key_true_numbers, trueNumbersList)
                    bundle.putIntegerArrayList(key_false_numbers, falseNumbersList)
                    bundle.putStringArrayList(key_recognized_words, recognizedWordsList)


                } else {

                    Toast.makeText(context, "音声を認識できませんでした", Toast.LENGTH_SHORT).show()

                }
            }
        })

        speechRecognizer.startListening(speechIntent)

        resultButton.setOnClickListener {
            val testResultFragment =TestResultFragment()

            val isPronounce = true
            bundle.putBoolean("isPronounce",isPronounce)

            testResultFragment.arguments = bundle

            val fragmentTransAction = fragmentManager!!.beginTransaction()
            fragmentTransAction.addToBackStack(null)
            fragmentTransAction.replace(R.id.testPronounceBackground,testResultFragment)
            fragmentTransAction.commit()
        }


        nextButton.setOnClickListener {
            val testPronounceFragment = TestPronounceFragment()
            testPronounceFragment.arguments = bundle

            val fragmentTransAction = fragmentManager!!.beginTransaction()
            fragmentTransAction.addToBackStack(null)
            fragmentTransAction.replace(R.id.testPronounceBackground, testPronounceFragment)
            fragmentTransAction.commit()

        }

        return layout
    }

}