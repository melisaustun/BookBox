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
import kotlinx.android.synthetic.main.item_bsproduct.view.*
import java.text.DecimalFormat

class BSBookAdapter() : RecyclerView.Adapter<BSBookAdapter.BSBookViewHolder>() {

    private var list: MutableList<ProductEntity> = mutableListOf()
    var onClickListener: OnClickItemAndAdd? =null

    inner class BSBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(productEntity: ProductEntity) {

            val price = productEntity.price
            val dec = DecimalFormat("#,###")
            val priceTL = dec.format(price)

            itemView.setOnClickListener {
                onClickListener?.onClick(productEntity)
            }

            itemView.btn_add_bsproduct.setOnClickListener {
                onClickListener?.onClickAdd(productEntity)
            }

            Glide.with(itemView).load(productEntity.picture).into(itemView.iv_picture_bsproduct)
            itemView.tv_name_bsproduct.text = productEntity.name
            itemView.tv_description_bsproduct.text = productEntity.description
            itemView.tv_price_bsproduct.text = "$priceTL TL"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BSBookViewHolder { //adapter oluşturulduğunda oluşturduğumuz ViewHolder'ın başlatılması için çağrılır
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bsproduct, parent, false)
        return BSBookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BSBookViewHolder, position: Int) { //dönen verileri bağlama işlemini gerçekleştir
        holder.bind(list[position])
    }

    override fun getItemCount(): Int { //listenin eleman sayısını döndür
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataAdapter(data: List<ProductEntity>) {  //veri kaynağından veri alır
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}