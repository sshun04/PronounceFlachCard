package com.shojishunsuke.pronounceflachcard.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.shojishunsuke.pronounceflachcard.fragment.FlashCardFragment
import com.shojishunsuke.pronounceflachcard.fragment.MemorizeWordFragment
import com.shojishunsuke.pronounceflachcard.testFragment.TestFragment


class MyPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FlashCardFragment()
            1 -> MemorizeWordFragment()
            2 -> TestFragment()
            else -> FlashCardFragment()
        }


    }

    override fun getCount(): Int = 3


    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {

            0 -> "単語帳"
            1 -> "覚える"
            2 -> "テスト"
            else -> null

        }
    }
}