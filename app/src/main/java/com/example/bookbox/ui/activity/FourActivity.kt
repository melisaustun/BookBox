package com.example.bookbox.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookbox.R

class FourActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_four)

        //actionbar
        supportActionBar?.hide()
    }
}