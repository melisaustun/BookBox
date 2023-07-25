package com.example.bookbox.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bookbox.R
import kotlinx.android.synthetic.main.activity_password.*

class PasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        //actionbar
        supportActionBar?.hide()

        forget()
    }

    private fun forget() {
        btn_update.setOnClickListener {
            when {
                email.text.isEmpty() -> {
                    email.error = null
                    email.requestFocus()
                    Toast.makeText(this, "Lütfen kayıtlı e-mail giriniz", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    startActivity(Intent(applicationContext, LoginActivity::class.java))
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    Toast.makeText(this, "Kod gönderildi. E-mailinizi kontrol ediniz.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }

}