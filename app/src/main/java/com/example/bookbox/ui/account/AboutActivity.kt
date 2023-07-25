package com.example.bookbox.ui.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookbox.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        //actionbar
        supportActionBar?.hide()
    }
}