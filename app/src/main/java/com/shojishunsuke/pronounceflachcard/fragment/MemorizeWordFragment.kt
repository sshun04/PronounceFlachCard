package com.shojishunsuke.pronounceflachcard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.adapter.MemorizeRecyclerViewAdapter
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.MemorizeFragmentViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.SharedViewModel


class MemorizeWordFragment : Fragment(), OnDataChangedListener {


    private val memorizeViewModel = MemorizeFragmentViewModel(this)

    lateinit var recyclerView: RecyclerView
    lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_memorize_word_tab, container, false)
        recyclerView = layout.findViewById<RecyclerView>(R.id.memorizeRecyclerView).apply {
            //    todo  SharedPreferenceからlistNameを取ってくる
            adapter = MemorizeRecyclerViewAdapter(context, memorizeViewModel.getWordsList(""))
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        }
       sharedViewModel = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        }?: throw Exception("Invalid Activity")

        sharedViewModel.currentTitle.observe(this, Observer { title ->
            //           リサイクラビューのアイテムを更新
            recyclerView.adapter = MemorizeRecyclerViewAdapter(context, memorizeViewModel.getWordsList(title))

        })

        return layout
    }


    override fun onDataChanged() {

        recyclerView.adapter = MemorizeRecyclerViewAdapter(context, memorizeViewModel.getWordsList(sharedViewModel.title))

    }


}




