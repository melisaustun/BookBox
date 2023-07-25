package com.example.bookbox.ui.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bookbox.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //actionbar
        supportActionBar?.hide()

        binding.btnKaydet.setOnClickListener {
            when {
                kayitKullaniciAdi.text.isEmpty() -> {
                    kayitKullaniciAdi.error = null
                    kayitKullaniciAdi.requestFocus()
                    Toast.makeText(this, "Kullanıcı adı giriniz", Toast.LENGTH_SHORT).show()
                }
                kayitEmail.text.isEmpty() -> {
                    kayitEmail.error = null
                    kayitEmail.requestFocus()
                    Toast.makeText(this, "Email giriniz", Toast.LENGTH_SHORT).show()
                }
                kayitSifre.text.isEmpty() -> {
                    kayitSifre.error = null
                    kayitSifre.requestFocus()
                    Toast.makeText(this, "Şifre giriniz", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val kullaniciBilgisi = binding.kayitKullaniciAdi.text.toString()
                    val kullaniciMail = binding.kayitEmail.text.toString()
                    val kullaniciSifre = binding.kayitSifre.text.toString()

                    val SharedPreferences = this.getSharedPreferences("bilgiler", MODE_PRIVATE) //verileri kaydeder
                    val editor = SharedPreferences.edit() //verileri günceller,siler

                    //veri ekleme
                    editor.putString("kullanici", "$kullaniciBilgisi").apply()
                    editor.putString("email", "$kullaniciMail").apply()
                    editor.putString("sifre", "$kullaniciSifre").apply()

                    //edittextlerin içindeki veriler silinir
                    binding.kayitKullaniciAdi.text.clear()
                    binding.kayitEmail.text.clear()
                    binding.kayitSifre.text.clear()
                    Toast.makeText(this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show()
                }
            }
        }
        girisedon()
    }

    private fun girisedon() {
        btnGiriseDon.setOnClickListener {
            intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
