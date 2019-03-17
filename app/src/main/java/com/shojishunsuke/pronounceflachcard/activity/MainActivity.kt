package com.shojishunsuke.pronounceflachcard.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.adapter.MyPagerAdapter

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout: TabLayout = this.findViewById(R.id.tablayout)
        val viewPager: ViewPager = this.findViewById(R.id.pager)
        val toolBar: androidx.appcompat.widget.Toolbar = this.findViewById(R.id.toolBar)
        toolBar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolBar)

        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_word_list))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_memorization))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_test))


        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter


        tabLayout.setupWithViewPager(viewPager)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

//                Log.d("page",state.toString())
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {


            }

            override fun onPageSelected(position: Int) {
                //      タブが切り替わった時の処理


                Log.d("select", position.toString())

                when (position) {
                    0 -> {
                        setTheme(R.style.MyCustomTheme_Default)

                    }
                    1 -> {
                        setTheme(R.style.MyCustomTheme_Memorize)

                    }
                    2 -> {
                        setTheme(R.style.MyCustomTheme_Test)

                    }
                }
            }
        })


    }


}
