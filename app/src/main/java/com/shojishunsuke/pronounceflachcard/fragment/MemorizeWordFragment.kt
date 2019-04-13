package com.shojishunsuke.pronounceflachcard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.adapter.MemorizeRecyclerViewAdapter
import io.realm.Realm
import io.realm.RealmResults


class MemorizeWordFragment : Fragment() {

    val realm = Realm.getDefaultInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_memorize_word_tab, container, false)

        val recyclerView = layout.findViewById<RecyclerView>(R.id.memorizeRecyclerView)

        val adapter = MemorizeRecyclerViewAdapter(context, read())

        realm.addChangeListener {

            adapter.notifyDataSetChanged()

        }

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)


        return layout
    }

    fun read(): RealmResults<WordObject> = realm.where(WordObject::class.java).findAll()


}




