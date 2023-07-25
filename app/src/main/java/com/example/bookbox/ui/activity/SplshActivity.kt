package com.example.bookbox.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.bookbox.R
import com.example.bookbox.onboarding.OnboardingExample2Activity

class SplshActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splsh)

        //actionbar
        supportActionBar?.hide()

        val timer = object: CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {

                startActivity(Intent(this@SplshActivity, OnboardingExample2Activity::class.java))
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
        timer.start()

    }

}