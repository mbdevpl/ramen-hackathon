package com.jirogo.kazuya.jiro

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_call -> {
                viewPager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_learn -> {
                viewPager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = object: FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> FirstFragment()
                    1 -> SecondFragment()
                    else -> FirstFragment()
                }
            }

            override fun getCount(): Int {
                return 2
            }
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
