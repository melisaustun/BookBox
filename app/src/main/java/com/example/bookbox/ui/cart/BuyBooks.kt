package com.example.bookbox.ui.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isEmpty
import com.example.bookbox.R
import com.example.bookbox.ui.activity.BuyActivity
import com.example.bookbox.utill.RentalInfo
import kotlinx.android.synthetic.main.activity_buy.view.*
import kotlinx.android.synthetic.main.activity_buy_books.*
import kotlinx.android.synthetic.main.activity_rent_books.*
import kotlinx.android.synthetic.main.activity_rent_books.view.*

class BuyBooks : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_books)

        //actionbar
        supportActionBar?.hide()

        btn_register2.setOnClickListener {
            if(checkDataValidation2()){
                et_name_rental2.text.clear()
                et_address2.text.clear()
                et_phone2.text.clear()

                startActivity(Intent(this, BuyActivity::class.java))
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                Toast.makeText(this, "Kaydedildi.", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "Lütfen yukarıdaki bilgileri doldurunuz.", Toast.LENGTH_SHORT).show()
            }
        }
        btn_cancel_rental2.setOnClickListener {
            setResult(RESULT_CANCELED, Intent(this, CartFragment::class.java))
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        }

    }
    private fun checkDataValidation2(): Boolean{

        var flag = true
        if(et_name_rental2.text.toString().isEmpty()){
            et_name_rental2.error = "Ad Soyad giriniz."
            flag = false
        }

        if(et_address2.text.toString().isEmpty()){
            et_address2.error = "Adres giriniz."
            flag = false
        }

        if(et_phone2.text.toString().isEmpty()){
            et_phone2.error = "Telefon numarası giriniz."
            flag = false
        }

        if(flag){
            RentalInfo.name = et_name_rental2.text.toString().trim()
            RentalInfo.address = et_address2.text.toString().trim()
            RentalInfo.phoneNumber = et_phone2.text.toString().trim().toLong()
            return true
        }
        return false
    }


}

