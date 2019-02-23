package com.shojishunsuke.pronounceflachcard.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shojishunsuke.pronounceflachcard.R

class FlashCardFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout  = inflater?.inflate(R.layout.fragment_flash_card_tab,container,false)

        return layout
    }
    companion object {
        fun newInstance (): FlashCardFragment {
            val fragment = FlashCardFragment()
            return fragment
        }
    }

}