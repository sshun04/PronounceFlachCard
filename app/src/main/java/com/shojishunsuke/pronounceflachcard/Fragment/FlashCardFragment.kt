package com.shojishunsuke.pronounceflachcard.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.WordObject
import com.shojishunsuke.pronounceflachcard.adapter.CardRecyclerViewAdapter
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_flash_card_tab.*

class FlashCardFragment : Fragment() {

    lateinit var realm: Realm


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout  = inflater.inflate(R.layout.fragment_flash_card_tab,container,false)

        val recyclerview = layout.findViewById<RecyclerView>(R.id.recyclerview)
        val addButton    = layout.findViewById<FloatingActionButton>(R.id.addwordbutton)

        realm = Realm.getDefaultInstance()

        recyclerview.adapter = CardRecyclerViewAdapter(context,read())
        recyclerview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)






        return layout
    }

    fun read():RealmResults<WordObject>
    {
        return realm.where(WordObject::class.java).findAll()


    }


}