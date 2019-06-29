package com.shojishunsuke.pronounceflachcard.activity

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.shojishunsuke.pronounceflachcard.R
import com.shojishunsuke.pronounceflachcard.adapter.MyPagerAdapter
import com.shojishunsuke.pronounceflachcard.new_arch.presentation.SharedViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedViewModel = run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        }

        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout).apply {
            setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START)
        }

        val tabLayout = findViewById<TabLayout>(R.id.tablayout)
        val viewPager = findViewById<ViewPager>(R.id.pager)
        val toolBar = findViewById<Toolbar>(R.id.toolbar).apply {
            title = sharedViewModel.title
        }
        setSupportActionBar(toolBar)

        tabLayout.apply {
            addTab(tabLayout.newTab().setText(R.string.tab_word_list))
            addTab(tabLayout.newTab().setText(R.string.tab_memorization))
            addTab(tabLayout.newTab().setText(R.string.tab_test))
        }

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter

        drawerToggle =
            object : ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.drawer_open, R.string.drawer_close) {
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

        sharedViewModel.liveDataTitle.observe(this, Observer {
            toolBar.title = it
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.navigation_drawer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }
}
