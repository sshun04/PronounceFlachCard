package com.shojishunsuke.pronounceflachcard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.adapter.DrawerRecyclerViewAdapter
import com.shojishunsuke.pronounceflachcard.new_arch.factory.SharedViewModelFactory
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.DrawerViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.SharedViewModel

class DrawerFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val layout = inflater.inflate(R.layout.fragment_drawer, container, false)

        val drawerViewModel = activity?.run {
            ViewModelProviders.of(this).get(DrawerViewModel::class.java)
        } ?: throw Exception("Invalid Activity")


        val headerTextView = layout.findViewById<TextView>(R.id.headerTextView)
        val registerButton = layout.findViewById<MaterialButton>(R.id.registerButton)
        val titleList = layout.findViewById<RecyclerView>(R.id.titleList).apply {
            adapter = DrawerRecyclerViewAdapter(activity!!,context!!,drawerViewModel.loadTitleList())
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }



        val sharedViewModel = activity?.run {
            ViewModelProviders.of(
                this,
                SharedViewModelFactory(context!!)
            ).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")



        sharedViewModel.currentTitle.observe(this, Observer { title ->
            headerTextView.text = title
        })


        drawerViewModel.currentAdapter.observe(this, Observer { recyclerViewAdapter ->

            titleList.adapter = recyclerViewAdapter
        })

        registerButton.setOnClickListener {
            drawerViewModel.popupRegisterDialogFragment(context!!, activity!!)
        }

        return layout

    }


}