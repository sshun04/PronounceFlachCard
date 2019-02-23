package com.shojishunsuke.pronounceflachcard.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.shojishunsuke.pronounceflachcard.R

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tablayout)
        viewPager = findViewById(R.id.pager)

        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_add_word))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_check_meaning))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_test))
        tabLayout.tabGravity


        viewPager.adapter = object : PagerAdapter(supportFragmentManager)

    }
}
