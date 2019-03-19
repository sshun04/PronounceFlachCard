package com.shojishunsuke.pronounceflachcard.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.WordObject
import io.realm.Realm
import io.realm.RealmResults


class TestMeaningFragment:Fragment() {

    val realm = Realm.getDefaultInstance()
    lateinit var checkedWords :RealmResults<WordObject>
    lateinit var allWords : RealmResults<WordObject>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout  = inflater.inflate(R.layout.fragment_test_meaning,container,false)

        val textView = layout.findViewById<TextView>(R.id.meaningTextView3)

        checkedWords = realm.where(WordObject::class.java).equalTo("isDone",true).findAll()
        allWords = realm.where(WordObject::class.java).findAll()

        if (arguments != null){
            textView.setText("meaning"+ ":"+ arguments!!.getBoolean("isChecked").toString())
            Log.d("words",checkedWords.count().toString() +"/"+ allWords.count().toString())
        }else{
            Log.d("Log","null")
        }

        return layout
    }
}