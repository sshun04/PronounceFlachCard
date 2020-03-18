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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.adapter.CardRecyclerViewAdapter
import com.shojishunsuke.pronounceflachcard.new_arch.data.repository.OnDataChangedListener
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.FlashCardFragmentViewModel
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.SharedViewModel

class FlashCardFragment : Fragment(), OnDataChangedListener {


    lateinit var recyclerView: RecyclerView
    lateinit var sharedViewModel: SharedViewModel

    private val flashCardViewModel = FlashCardFragmentViewModel(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_flash_card_tab, container, false)

        sharedViewModel = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        recyclerView = layout.findViewById<RecyclerView>(R.id.recyclerview).apply {
            adapter = CardRecyclerViewAdapter(requireContext(), flashCardViewModel.getWordsList(sharedViewModel.title))
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }

        val addButton = layout.findViewById<FloatingActionButton>(R.id.addwordbutton)

        addButton.setImageResource(R.drawable.baseline_add_white_18)
        addButton.setOnClickListener(View.OnClickListener {

            flashCardViewModel.setupAddWordDialogFragment(childFragmentManager)

        })


        sharedViewModel.liveDataTitle.observe(this, Observer { title ->
            //           リサイクラビューのアイテムを更新
            recyclerView.adapter = CardRecyclerViewAdapter(requireContext(), flashCardViewModel.getWordsList(title))

        })



        return layout
    }

    override fun onDataChanged() {}


}