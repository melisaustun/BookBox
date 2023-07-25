package com.example.bookbox.ui.activity


import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bookbox.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //actionbar
        supportActionBar?.hide()

        preferences = getSharedPreferences("bilgiler", MODE_PRIVATE)
        //bilgileri çekiyor

        binding.btnGirisYap.setOnClickListener {
            var kayitliKullanici = preferences.getString("kullanici", "")
            val kayitliEmail = preferences.getString("email", "")
            val kayitliSifre = preferences.getString("sifre", "")

            val girisMail = binding.girisEmail.text.toString()
            val girisSifre = binding.girisSifre.text.toString()

            if ((kayitliEmail == girisMail) && (kayitliSifre == girisSifre)) {
                intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            } else {
                Toast.makeText(applicationContext, "Giriş Bilgileri Hatalı", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnKayitOl.setOnClickListener {
            intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

        binding.sifremiunuttum.setOnClickListener {
            intent = Intent(applicationContext, PasswordActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }

}






