package com.shojishunsuke.pronounceflachcard.activity

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.adapter.MyPagerAdapter
import io.realm.Realm


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerList:RecyclerView
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var newTitle:CharSequence





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var drawerTitle = title

        newTitle = title
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout).apply {
            setDrawerShadow(R.drawable.drawer_shadow,GravityCompat.START)
        }

        drawerList = findViewById<RecyclerView>(R.id.left_drawer).apply {
            setHasFixedSize(true)
        }

        val tabLayout: TabLayout = this.findViewById(R.id.tablayout)
        val viewPager: ViewPager = this.findViewById(R.id.pager)
        val toolBar: androidx.appcompat.widget.Toolbar = this.findViewById(R.id.toolabar)
        setSupportActionBar(toolBar)



        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_word_list))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_memorization))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_test))


        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter


        drawerToggle = object :ActionBarDrawerToggle(this,drawerLayout,toolBar,R.string.drawer_open,R.string.drawer_close){
            override fun onDrawerClosed(drawerView: View) {

                invalidateOptionsMenu()
            }

            override fun onDrawerOpened(drawerView: View) {

                invalidateOptionsMenu()
            }
        }
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()


        tabLayout.setupWithViewPager(viewPager)



    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean{
        menuInflater.inflate(R.menu.navigation_drawer,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true
        }

      return  super.onOptionsItemSelected(item)
    }



    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }
}
