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
import androidx.fragment.app.Fragment
import com.shojishunsuke.pronounceflachcard.Model.QuestionWord
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.R
import io.realm.Realm
import io.realm.RealmResults

class TestPronounceFragment : Fragment() {
    val realm = Realm.getDefaultInstance()

    private lateinit var showingCards: RealmResults<WordObject>
    private lateinit var showingWord: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_test_pronounce, container, false)
        val textView = layout.findViewById<TextView>(R.id.wordTextView4)
        val nextButton = layout.findViewById<Button>(R.id.nextButton)
        val resultButton = layout.findViewById<Button>(R.id.resultButton)
        val confirmTextView = layout.findViewById<TextView>(R.id.textView1)


        val key_checked = resources.getString(R.string.key_is_checked_Only)
        val key_question_number = resources.getString(R.string.key_question_number)
        val key_quesiton_words = resources.getString(R.string.key_question_words)


        var questionNumber = arguments!!.getInt(key_question_number)
        val isCheckedOnly = arguments!!.getBoolean(key_checked)
        val questionWordsList = arguments!!.getSerializable(key_quesiton_words) as ArrayList<QuestionWord>

        showingCards = if (isCheckedOnly) realm.where(WordObject::class.java).equalTo("isDone", true).findAll()
        else realm.where(WordObject::class.java).findAll()

        showingWord = showingCards.get(questionNumber)!!.word
        val meaning = showingCards.get(questionNumber)!!.meaning

        textView.setText(showingWord)


        val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        speechIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context!!.packageName)

        val bundle = Bundle()
        val questionWord = QuestionWord()

        val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

        speechRecognizer.setRecognitionListener(object : RecognitionListener {

            override fun onReadyForSpeech(p0: Bundle?) {

                Log.d("PronounceActivity", "ready")

            }
            override fun onRmsChanged(p0: Float) {}
            override fun onBufferReceived(p0: ByteArray?) {}
            override fun onPartialResults(p0: Bundle?) {}
            override fun onEvent(p0: Int, p1: Bundle?) {}
            override fun onBeginningOfSpeech() {}

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


                questionWord.word = showingWord
                questionWord.meaning = meaning
                questionWord.questionNumber = questionNumber
                questionWord.isPronounce = true

                if (stringList.contains(showingWord)) {

                    questionWord.isTrue = true
                    questionWord.recognizedWord = showingWord

                } else {

                    questionWord.isTrue = false
                    questionWord.recognizedWord = stringList.get(0)

                }

                speechRecognizer.destroy()

                confirmTextView.visibility = View.GONE



                if (showingCards.count() == questionNumber + 1) {

                    resultButton.visibility = View.VISIBLE

                } else {
                    questionNumber++
                    nextButton.visibility = View.VISIBLE

                }

                questionWordsList.add(questionWord)

                bundle.putInt(key_question_number, questionNumber)
                bundle.putBoolean(key_checked, isCheckedOnly)
                bundle.putSerializable(key_quesiton_words, questionWordsList)


            }


        })

        resultButton.setOnClickListener {
            val testResultFragment = TestResultFragment()

            replace(testResultFragment, bundle)

        }


        nextButton.setOnClickListener {
            val testPronounceFragment = TestPronounceFragment()

            replace(testPronounceFragment, bundle)

        }

        speechRecognizer.startListening(speechIntent)

        return layout
    }

    private fun replace(fragment: Fragment, bundle: Bundle) {
        fragment.arguments = bundle
        val fragmentTransAction = fragmentManager!!.beginTransaction()
        fragmentTransAction.addToBackStack("null")
        fragmentTransAction.replace(R.id.testPronounceBackground, fragment)
        fragmentTransAction.commit()

    }

}