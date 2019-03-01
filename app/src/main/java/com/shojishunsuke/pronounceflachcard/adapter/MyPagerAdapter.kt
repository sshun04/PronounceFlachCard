package com.shojishunsuke.pronounceflachcard.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.shojishunsuke.pronounceflachcard.Fragment.AddWordFragment
import com.shojishunsuke.pronounceflachcard.Fragment.FlashCardFragment
import com.shojishunsuke.pronounceflachcard.Fragment.TestFragment


class MyPagerAdapter(fragmentManager: FragmentManager) :FragmentPagerAdapter(fragmentManager) {




    override fun getItem(position: Int): Fragment {
        when (position) {
            0 ->
            {return AddWordFragment()}

            1 ->
            { return FlashCardFragment()}
            2
            ->
            { return TestFragment()}

            else -> {return AddWordFragment()}
        }


    }

    override fun getCount(): Int {
        return 3

    }


    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {

            0 -> return "AddWord"
            1 -> return "FlashCard"
            2 -> return "Test"
            else -> return null

        }
    }
}