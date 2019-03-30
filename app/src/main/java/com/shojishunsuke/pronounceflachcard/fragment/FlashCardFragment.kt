package com.shojishunsuke.pronounceflachcard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.Model.WordObject
import com.shojishunsuke.pronounceflachcard.adapter.CardRecyclerViewAdapter
import io.realm.Realm
import io.realm.RealmResults

class FlashCardFragment : Fragment() {
    val realm = Realm.getDefaultInstance()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_flash_card_tab, container, false)

        val recyclerview = layout.findViewById<RecyclerView>(R.id.recyclerview)
        val addButton = layout.findViewById<FloatingActionButton>(R.id.addwordbutton)

        addButton.setImageResource(R.drawable.baseline_add_white_18)
        addButton.setOnClickListener(View.OnClickListener {
            val addDialog = AddWordDialogFragment()
            addDialog.show(childFragmentManager,"add_dialog")
        })

        val getData = read()
        recyclerview.adapter = CardRecyclerViewAdapter(context,getData)


        recyclerview.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)


        return layout
    }


    fun read(): RealmResults<WordObject> {

        return realm.where(WordObject::class.java).findAll()

    }


}