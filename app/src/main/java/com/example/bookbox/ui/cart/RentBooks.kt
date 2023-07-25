package com.example.bookbox.ui.cart

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import com.example.bookbox.R
import com.example.bookbox.ui.activity.BuyActivity
import com.example.bookbox.utill.RentalInfo
import kotlinx.android.synthetic.main.activity_rent_books.*
import kotlinx.android.synthetic.main.activity_rent_books.view.*
import java.util.*

class RentBooks : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var languages = arrayOf("Seçiniz...", "5 gün", "7 gün", "10 gün")

    private val registerDriverReqCode = 1
    private val prevHistoryReqCode = 2

    private var address: String? = "hell"
    @SuppressLint("StringFormatInvalid")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rent_books)

        //actionbar
        supportActionBar?.hide()

        var aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(mySpinner)
        {
            adapter = aa
            setSelection(0, false)
            onItemSelectedListener = this@RentBooks
            prompt = "Kiralama süresi seçiniz"
            gravity = Gravity.CENTER
        }

        btn_register.setOnClickListener {
            if(checkDataValidation()){
                et_name_rental.text.clear()
                et_address.text.clear()
                et_phone.text.clear()


                startActivity(Intent(this, BuyActivity::class.java))
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

                Toast.makeText(this, "Kaydedildi.", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "Lütfen yukarıdaki bilgileri doldurunuz.", Toast.LENGTH_SHORT).show()
            }
        }
        btn_cancel_rental.setOnClickListener {
            setResult(RESULT_CANCELED, Intent(this, CartFragment::class.java))
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        showToast(message = "Seçim yapmadınız.")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        when (view?.id) {
            else -> {
            }
        }
    }

    private fun showToast(context: Context = applicationContext, message: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(context, message, duration).show()
    }

    private fun checkDataValidation(): Boolean{

        var flag = true
        if(et_name_rental.text.toString().isEmpty()){
            et_name_rental.error = "Ad Soyad giriniz."
            flag = false
        }

        if(et_address.text.toString().isEmpty()){
            et_address.error = "Adres giriniz."
            flag = false
        }

        if(et_phone.text.toString().isEmpty()){
            et_phone.error = "Telefon numarası giriniz"
            flag = false
        }

        if(flag){
            RentalInfo.name = et_name_rental.text.toString().trim()
            RentalInfo.address = et_address.text.toString().trim()
            RentalInfo.phoneNumber = et_phone.text.toString().trim().toLong()
            return true
        }
        return false
    }
}