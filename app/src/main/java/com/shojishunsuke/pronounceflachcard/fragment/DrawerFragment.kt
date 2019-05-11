package com.shojishunsuke.pronounceflachcard.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.shojishunsuke.pronounceflachcard.R

class DrawerFragment:Fragment() {

//    作られた単語帳一覧の表示と単語帳の追加ボタンを配置する

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val layout = inflater.inflate(R.layout.fragment_drawer,container,false)
        val headerTextView = layout.findViewById<TextView>(R.id.headerTextView)
        val listview = layout.findViewById<ListView>(R.id.flashCardsList)




        return layout

    }

}