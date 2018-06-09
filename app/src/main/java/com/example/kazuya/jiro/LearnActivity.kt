package com.example.kazuya.jiro

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MenuInflater
import android.view.Menu



class LearnActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)

        // this.
        // navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        // navigation.
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.navigation, menu)
        return true
    }

}
