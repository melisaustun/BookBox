package com.example.bookbox.utill

import com.example.bookbox.utill.Constants.OPEN_ACCOUNT
import com.example.bookbox.utill.Constants.OPEN_NUMBER
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..1).random()
        val random2 = (0..0).random()
        val message = _message.lowercase(Locale.ROOT)

        return when {
            message.contains("son") && message.contains("alışverişim") -> {
                val r = (0..1).random()
                val result = if (r == 0) "kiralama" else "satın alma"

                "En son yaptığınız alışveriş $result"
            }


            message.contains("siparişimin") && message.contains("durumu") && message.contains("nedir") -> {
                when (random) {
                    0 -> "Siparişinizi aldık, kargoya verilmek üzere hazırlanıyor."
                    1 -> "Siparişiniz kargoya verildi."
                    2 -> "Siparişiniz teslim edilmek üzere yola çıktı."
                    else -> "hata" }
            }


            message.contains("kiralama") && message.contains("hakkında") && message.contains("bilgi") && message.contains("alabilir miyim")-> {
                when (random2) {
                    0 -> "Tabii, kiralamada iade konusunda problem olmaması adına kiralamanız satın alma bedeli üzerinden gerçekleştirilecek." +
                     "Kiralamadan kalan ücretiniz kitabı kargoya verdiğinizde ve kitap bize ulaştığında kartınıza yatırılacak." +
                     "Kitabı iade edene kadar satın almış sayılacaksınız."
                    else -> "hata"
                }
            }

            message.contains("saat") && message.contains("kaç")-> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }


            message.contains("sohbeti") && message.contains("sonlandır")-> {
                OPEN_ACCOUNT
            }


            message.contains("müşteri") && message.contains("temsilcisi")-> {
                OPEN_NUMBER
            }

            //Program anlamadığında
            else -> {
                when (random) {
                    0 -> "Anlayamadım..."
                    1 -> "Farklı bir soru sormayı deneyin."
                    2 -> "Üzgünüm, bu sorunun cevabını bilmiyorum."
                    else -> "hata"
                }
            }
        }
    }
}