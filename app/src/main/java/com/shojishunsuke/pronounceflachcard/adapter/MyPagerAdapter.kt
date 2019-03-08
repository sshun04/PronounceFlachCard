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
            {return FlashCardFragment()}

            1 ->
            { return TestFragment()}

            else -> {return AddWordFragment()}
        }


    }

    override fun getCount(): Int {
        return 2

    }


    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {


            0 -> return "単語帳"
            1 -> return "テスト"
            else -> return null

        }
    }
}