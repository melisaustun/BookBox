package com.example.bookbox.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.bookbox.R
import com.example.bookbox.ui.account.AccountFragment
import com.example.bookbox.ui.cart.CartFragment
import com.example.bookbox.ui.explore.ExploreFragment
import com.example.bookbox.ui.favorite.FavoriteFragment
import com.example.bookbox.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //actionbar
        supportActionBar?.hide()

        homeFragment()
        BottomNavigationBar()
    }


    private fun BottomNavigationBar() {
        bottom_nav_bar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> homeFragment()
                R.id.explore -> exploreFragment()
                R.id.cart -> cartFragment()
                R.id.favorite -> favoriteFragment()
                R.id.account -> accountFragment()
            }
            true
        }
    }

    private fun homeFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, HomeFragment()).commit()
    }
    private fun exploreFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, ExploreFragment()).commit()
    }
    private fun cartFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, CartFragment()).commit()
    }
    private fun favoriteFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, FavoriteFragment()).commit()
    }
    private fun accountFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, AccountFragment()).commit()
    }

    fun navigateExplore(){
        bottom_nav_bar.selectedItemId = R.id.explore
    }

}