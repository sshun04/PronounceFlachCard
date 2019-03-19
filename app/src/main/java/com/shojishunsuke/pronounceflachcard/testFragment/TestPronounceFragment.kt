package com.shojishunsuke.pronounceflachcard.testFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.shojishunsuke.pronounceflachcard.R

class TestPronounceFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_test_pronounce, container, false)

        val textView = layout.findViewById<TextView>(R.id.textView)

        if (arguments != null) {

            textView.setText("Pronounce" +":"+ arguments!!.getBoolean("isChecked").toString())

        } else {
            Log.d("Log", "null")
        }
        return layout
    }

}