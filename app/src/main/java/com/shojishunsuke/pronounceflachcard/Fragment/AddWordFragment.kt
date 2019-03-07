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

lateinit var addButton: Button
lateinit var wordEditText: EditText
lateinit var meaningEditText: EditText
lateinit var deleteButton: Button
lateinit var wordCard: WordObject
val realm: Realm = Realm.getDefaultInstance()


class AddWordFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_add_word_tab, container, false)

        addButton = layout.findViewById(R.id.addButton)
        wordEditText = layout.findViewById(R.id.wordEditText)
        meaningEditText = layout.findViewById(R.id.meaningEditText)
        deleteButton = layout.findViewById(R.id.deleteButton)





        addButton.setOnClickListener(View.OnClickListener {
            addWord(wordEditText.text.toString(), meaningEditText.text.toString())

        })

        deleteButton.setOnClickListener(View.OnClickListener {

        })

        return layout
    }

    fun addWord(editWord: String, editMeaning: String) {


        realm.executeTransaction {

            wordCard = realm.createObject(WordObject::class.java)

            wordCard.word = editWord
            wordCard.meaning = editMeaning

            realm.copyToRealm(wordCard)
        }
    }


}

fun deleteWord() {}
