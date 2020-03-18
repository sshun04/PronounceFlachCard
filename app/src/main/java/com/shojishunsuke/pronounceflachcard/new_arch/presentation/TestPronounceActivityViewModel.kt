package com.shojishunsuke.pronounceflachcard.new_arch.presentation

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.testFragment.TestPronounceFragment

class TestPronounceActivityViewModel : ViewModel() {

    fun setupFragment(supportFragmentManager:FragmentManager) {
        val testPronounceFragment = TestPronounceFragment()

        val fragmentTransAction = supportFragmentManager.beginTransaction()
        fragmentTransAction.addToBackStack(null)
        fragmentTransAction.replace(R.id.testPronounceBackground, testPronounceFragment)
        fragmentTransAction.commit()
    }
}