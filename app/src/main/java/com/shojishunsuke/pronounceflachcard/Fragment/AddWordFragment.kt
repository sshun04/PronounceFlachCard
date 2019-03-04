package com.shojishunsuke.pronounceflachcard.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.WordObject
import io.realm.Realm
import java.util.*

lateinit var realm: Realm
lateinit var addButton: Button
lateinit var worEditText: EditText
lateinit var meaningEditText: EditText

class AddWordFragment:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_add_word_tab,container,false)

        addButton = layout.findViewById(R.id.addButton)
        worEditText = layout.findViewById(R.id.wordEditText)
        meaningEditText = layout.findViewById(R.id.meaningEditText)

        realm = Realm.getDefaultInstance()

        addButton.setOnClickListener(View.OnClickListener {
            addWord(worEditText.toString(), meaningEditText.toString())

        })

        return layout
    }

    fun addWord(word:String, meaning:String){

        realm.executeTransaction{
            var newWord = realm.createObject(WordObject::class.java, word)
            newWord.word = word
            newWord.meaning= meaning

            realm.copyToRealm(newWord)
        }


    }

}