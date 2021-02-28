package com.updown.superdo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.updown.superdo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        title = "Incoming Item Feed"
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}
