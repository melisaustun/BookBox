package com.example.bookbox.ui.activity

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookbox.ui.adapter.MessagingAdapter
import com.example.bookbox.R
import com.example.bookbox.data.Message
import com.example.bookbox.ui.account.AccountFragment
import com.example.bookbox.utill.Constants.RECEIVE_ID
import com.example.bookbox.utill.Constants.SEND_ID
import com.example.bookbox.utill.BotResponse
import com.example.bookbox.utill.Constants.OPEN_ACCOUNT
import com.example.bookbox.utill.Constants.OPEN_NUMBER
import com.example.bookbox.utill.Time
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.coroutines.*

class ChatActivity : AppCompatActivity() {
    private val TAG = "ChatActivity"

    private var messagesList = mutableListOf<Message>()

    private lateinit var adapter: MessagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorPrimary)))
        supportActionBar?.setLogo(R.drawable.chat)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayUseLogoEnabled(true)

        recyclerView()
        clickEvents()

        customBotMessage("Merhaba! ben BookBox Asistan, nasıl yardımcı olabilirim?")
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun clickEvents() {

        //mesaj gönderme
        btn_send.setOnClickListener {
            sendMessage()
        }

        //metine tıklandığında doğru konuma geri kaydırma
        et_message.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)

                }
            }
        }
    }

    private fun recyclerView() {
        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)

    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onStart() {
        super.onStart()
        //Mesaj olması durumunda, uygulamayı yeniden açarken en alta kaydırma
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun sendMessage() {
        val message = et_message.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            // Yerel listeye ekler
            messagesList.add(Message(message, SEND_ID, timeStamp))
            et_message.setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            rv_messages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            //Fake yanıt gecikmesi
            delay(1000)

            withContext(Dispatchers.Main) {

                //Yanıtı alır
                val response = BotResponse.basicResponses(message)

                // Yerel listeye ekler
                messagesList.add(Message(response, RECEIVE_ID, timeStamp))

                //Mesajımı bağdaştırıcıya ekler
                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))

                //En son mesajın konumuna kaydırır
                rv_messages.scrollToPosition(adapter.itemCount - 1)

                //account fragmentı ve arama ekranını açma
                when (response) {
                    OPEN_ACCOUNT -> {
                        intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                        finish()
                    }
                    OPEN_NUMBER -> {
                        val phone: String = message.substringAfterLast("müşteri temsilcisi")
                        startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")))
                    }

                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun customBotMessage(message: String) {

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                messagesList.add(Message(message, RECEIVE_ID, timeStamp))
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))

                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
}