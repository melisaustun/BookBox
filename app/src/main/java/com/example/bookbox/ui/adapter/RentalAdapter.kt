package com.example.bookbox.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookbox.R
import com.example.bookbox.listener.OnClickItemAndAdd
import com.example.bookbox.model.product.ProductEntity
import kotlinx.android.synthetic.main.item_rental.view.*
import java.text.DecimalFormat

class RentalAdapter : RecyclerView.Adapter<RentalAdapter.RentalViewHolder>() {

    private var list: MutableList<ProductEntity> = mutableListOf()
    var onClickListener: OnClickItemAndAdd? =null

    inner class RentalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(productEntity: ProductEntity) {

            val price = productEntity.price
            val dec = DecimalFormat("#,###")
            val priceTL = dec.format(price)

            itemView.setOnClickListener {
                onClickListener?.onClick(productEntity)
            }

            itemView.btn_rental_add_cart.setOnClickListener {
                onClickListener?.onClickAdd(productEntity)
            }
            //productEntity tablosunda tanımlanan nesneleri çeker
            Glide.with(itemView).load(productEntity.picture).into(itemView.iv_picture)
            itemView.tv_name.text = productEntity.name
            itemView.tv_description.text = productEntity.description
            itemView.tv_price.text = "$priceTL TL"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RentalViewHolder { //adapter oluşturulduğunda oluşturduğumuz ViewHolder'ın başlatılması için çağrılır
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rental, parent, false)
        return RentalViewHolder(view)
    }

    override fun onBindViewHolder(holder: RentalViewHolder, position: Int) {//dönen verileri bağlama işlemini gerçekleştir
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {  //listenin eleman sayısını döndür
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataAdapter(data: List<ProductEntity>) {//veri kaynağından veri alır
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}