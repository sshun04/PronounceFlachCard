package com.shojishunsuke.pronounceflachcard.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.shojishunsuke.pronounceflachcard.Fragment.FlashCardFragment
import com.shojishunsuke.pronounceflachcard.Fragment.MemorizeWordFragment
import com.shojishunsuke.pronounceflachcard.Fragment.TestFragment


class MyPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {

                return FlashCardFragment()
            }

            1 -> {
                return MemorizeWordFragment()
            }

            2 -> {
                return TestFragment()
            }

            else -> {
                return FlashCardFragment()
            }
        }


    }

    override fun getCount(): Int {
        return 3

    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {


            0 -> return "単語帳"
            1 -> return "覚える"
            2 -> return "テスト"
            else -> return null

        }
    }
}