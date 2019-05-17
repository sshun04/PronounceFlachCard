package com.shojishunsuke.pronounceflachcard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.adapter.DrawerRecyclerViewAdapter
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.DrawerViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.SharedViewModel
import kotlinx.android.synthetic.main.fragment_word_edit.*

class DrawerFragment:Fragment(),OnDataChangedListener {


    private val drawerViewModel = DrawerViewModel(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        val layout = inflater.inflate(R.layout.fragment_drawer,container,false)
        val headerTextView = layout.findViewById<TextView>(R.id.headerTextView)
        val registerButton = layout.findViewById<MaterialButton>(R.id.registerButton)
        val titleList = layout.findViewById<RecyclerView>(R.id.titleList).apply {
            adapter = DrawerRecyclerViewAdapter(this@DrawerFragment.activity!!,context,drawerViewModel.loadTitleList())
            layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        }

        val sharedViewModel = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        }?: throw Exception("Invalid Activity")


        sharedViewModel.currentTitle.observe(this, Observer {
            title -> headerTextView.text = title
        })


        registerButton.setOnClickListener {
            drawerViewModel.popupRegisterDialogFragment(context!!)
        }

        return layout

    }

    override fun onDataChanged() {

    }

}