package com.shojishunsuke.pronounceflachcard.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.WordObject
import io.realm.Realm
import io.realm.RealmResults

class TestFragment:Fragment() {

    val realm = Realm.getDefaultInstance()
    lateinit var checkedWords : RealmResults<WordObject>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_test_tab, container, false)




        realm.addChangeListener {

           checkedWords =  realm.where(WordObject::class.java).equalTo("isDone",true).findAll()

            Log.d("a",checkedWords.count().toString())

        }

        return layout
    }
}