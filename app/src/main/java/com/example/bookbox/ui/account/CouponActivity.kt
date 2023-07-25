package com.example.bookbox.ui.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookbox.R

class CouponActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon)
        //actionbar
        supportActionBar?.hide()
    }
}