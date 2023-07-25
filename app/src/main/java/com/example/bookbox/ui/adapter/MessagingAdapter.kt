package com.example.bookbox.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookbox.R
import com.example.bookbox.data.Message
import com.example.bookbox.utill.Constants.RECEIVE_ID
import com.example.bookbox.utill.Constants.SEND_ID
import kotlinx.android.synthetic.main.message_item.view.*

class MessagingAdapter: RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {

    var messagesList = mutableListOf<Message>()

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {

                //Tıklanan öğedeki mesajı kaldırma
                messagesList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder { //adapter oluşturulduğunda oluşturduğumuz ViewHolder'ın başlatılması için çağrılır
        return MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        )
    }

    override fun getItemCount(): Int { //listenin eleman sayısını döndür
        return messagesList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) { //dönen verileri bağlama işlemini gerçekleştir
        val currentMessage = messagesList[position]

        when (currentMessage.id) {
            SEND_ID -> {
                holder.itemView.tv_message.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.tv_bot_message.visibility = View.GONE
            }
            RECEIVE_ID -> {
                holder.itemView.tv_bot_message.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.tv_message.visibility = View.GONE
            }
        }
    }

    fun insertMessage(message: Message) { //veri kaynağından veri alır
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
    }

}