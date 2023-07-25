package com.example.bookbox.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import com.example.bookbox.R
import kotlinx.android.synthetic.main.activity_buy.*
import kotlinx.android.synthetic.main.finish_view.view.*


class BuyActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

        //actionbar
        supportActionBar?.hide()

        val checkBox1 = findViewById<CheckBox>(R.id.checkboxone) as CheckBox
        val checkBox2 = findViewById<CheckBox>(R.id.checkboxtwo) as CheckBox
        val btn = findViewById<Button>(R.id.btncheckbox) as Button

        btn.setOnClickListener {
            if (checkBox1.isChecked == true && checkBox2.isChecked == true) {
            }
            else {
                Toast.makeText(this, "Lütfen Ön Bilgilendirme Koşullarını ve Mesafeli Satış Sözleşmesini onaylayınız.", Toast.LENGTH_SHORT).show()
            }
        }
        card()
    }

   private fun card() {
        checkout.setOnClickListener {
            when {
                kart_ad_soyad.text.isEmpty() -> {
                    kart_ad_soyad.error = null
                    kart_ad_soyad.requestFocus()
                    Toast.makeText(this, "Lütfen kartın üzerindeki adı soyadı girin.", Toast.LENGTH_SHORT).show()
                }
                kart_numarasi.text.isEmpty() -> {
                    kart_numarasi.error = null
                    kart_numarasi.requestFocus()
                    Toast.makeText(this, "Lütfen kart numarası girin", Toast.LENGTH_SHORT).show()
                }
                ayyil.text.isEmpty() -> {
                    ayyil.error = null
                    ayyil.requestFocus()
                    Toast.makeText(this, "Lütfen son kullanma tarihi giriniz", Toast.LENGTH_SHORT).show()
                }
                cvv.text.isEmpty() -> {
                    cvv.error = null
                    cvv.requestFocus()
                    Toast.makeText(this, " Lütfen CVV giriniz", Toast.LENGTH_SHORT).show()
                }
                else ->
                {

            //başlar
            val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            // özel görünüm inflate eder
            val view = inflater.inflate(R.layout.finish_view,null)

            //Yeni bir açılır pencere örneği başlar
            val popupWindow = PopupWindow(
                view, // Açılır pencerede gösterilecek özel görünüm
                LinearLayout.LayoutParams.WRAP_CONTENT, // Açılır pencerenin genişliği
                LinearLayout.LayoutParams.WRAP_CONTENT // Açılır pencerenin yüksekliği

            )

            // Açılır pencere için bir yükseklik belirleme
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                popupWindow.elevation = 10.0F
            }


            // API seviyesi 23 veya daha yüksekse, kod çalışır
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                // Açılır pencere için yeni bir slayt animasyonu geçişi oluşturma
                val slideIn = Slide()
                slideIn.slideEdge = Gravity.TOP
                popupWindow.enterTransition = slideIn

                // Açılır pencere çıkış geçişi için slayt animasyonu
                val slideOut = Slide()
                slideOut.slideEdge = Gravity.LEFT
                popupWindow.exitTransition = slideOut

            }

            // Araç referansını özel görünümden alır
            val tv = view.popup_window_title
            val buttonPopup = view.popup_window_button

            // Açılır pencerenin görünümü için tıklama
            tv.setOnClickListener{
                // Açılır pencerenin metin görünümünün metin rengini değiştirme
                tv.setTextColor(Color.WHITE)
            }

            // Açılır pencerenin butonu için bir tıklama
            buttonPopup.setOnClickListener{
                // Açılır pencereyi kapat
                popupWindow.dismiss()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()

            }

            // Açılır pencereyi kapatma
            popupWindow.setOnDismissListener {
            }


            // Uygulamada açılır pencereyi gösterme
            TransitionManager.beginDelayedTransition(root_layout)
            popupWindow.showAtLocation(
                root_layout, // Açılır pencerenin görüntüleneceği konum
                Gravity.CENTER, // Açılır pencereyi görüntülemek için yerleşimin tam konumu
                0, // X offset
                0 // Y offset
            )

                }
            }
        }
    }
}