package com.shojishunsuke.pronounceflachcard.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
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
    lateinit var sharedViewModel: SharedViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedViewModel = run {
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
        when (item?.itemId) {
            R.id.actionEditListTitle -> {
                val parentView = layoutInflater.inflate(R.layout.dialog_fragment_register_title, null)
                val editText = parentView.findViewById<EditText>(R.id.registerEditText)

                val registerDialog = AlertDialog.Builder(this)
                    .setPositiveButton("登録", DialogInterface.OnClickListener { _, _ ->
                        sharedViewModel.editListTitle(editText.text.toString())

                    })
                    .setNegativeButton(
                        "キャンセル",
                        null
                    )
                    .create()


                registerDialog.setView(parentView)
                registerDialog.show()
            }

            R.id.actionDeleteList -> {

            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }
}
