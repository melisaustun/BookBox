package com.example.bookbox.onboarding


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.bookbox.R
import com.google.android.material.tabs.TabLayoutMediator
import com.example.bookbox.databinding.ActivityOnboardingExample2Binding
import com.example.bookbox.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.activity_onboarding_example2.*

class OnboardingExample2Activity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager2
    private lateinit var btnCreateAccount: Button

    private lateinit var binding: ActivityOnboardingExample2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingExample2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //actionbar
        supportActionBar?.hide()

        btnCreateAccount = binding.btnCreateAccount
        btnCreateAccount.setOnClickListener {
            finish()
        }
        //geçiş
        mViewPager = findViewById(R.id.viewPager)
        mViewPager.adapter = OnboardingViewPagerAdapter2(this, this)
        TabLayoutMediator(binding.pageIndicator, mViewPager) { _, _ -> }.attach()
        mViewPager.offscreenPageLimit = 1

        btn_create_account.setOnClickListener {
            intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.bottom, R.anim.top)
        }


    }
}
