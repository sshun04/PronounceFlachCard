package com.shojishunsuke.pronounceflachcard.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.adapter.MyPagerAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout : TabLayout = this.findViewById(R.id.tablayout)
        val viewPager : ViewPager = this.findViewById(R.id.pager)

        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_word_list))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_memorization))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_test))


        val fragmentAdapter  = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)



    }


}
