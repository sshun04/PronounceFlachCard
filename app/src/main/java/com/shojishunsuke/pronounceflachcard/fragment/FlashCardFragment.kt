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
//    val realm = Realm.getDefaultInstance()


    private val flashCardViewModel = FlashCardFragmentViewModel(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_flash_card_tab, container, false)

        val recyclerView = layout.findViewById<RecyclerView>(R.id.recyclerview).apply {

            //        todo  SharedPreferenceからlistNameを取得する
            adapter = CardRecyclerViewAdapter(context, flashCardViewModel.getWordsList(" "))
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)


        }
        val addButton = layout.findViewById<FloatingActionButton>(R.id.addwordbutton)

        addButton.setImageResource(R.drawable.baseline_add_white_18)
        addButton.setOnClickListener(View.OnClickListener {
            //            val addDialog = AddWordDialogFragment()
//            addDialog.show(childFragmentManager,"add_dialog")

            flashCardViewModel.setupAddWordDialogFragment(childFragmentManager)

        })

        val sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)

        sharedViewModel.currentTilte.observe(this, Observer { title ->
            //           リサイクラビューのアイテムを更新
            recyclerView.adapter = CardRecyclerViewAdapter(context, flashCardViewModel.getWordsList(title))
        })



        return layout
    }

    override fun onDataChanged() {

    }
    //    private  fun read(): RealmResults<WordObject> = realm.where(WordObject::class.java).findAll()


}