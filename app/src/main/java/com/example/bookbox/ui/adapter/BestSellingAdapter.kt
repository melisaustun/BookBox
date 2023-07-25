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
import kotlinx.android.synthetic.main.item_best_selling.view.*
import java.text.DecimalFormat

class BestSellingAdapter : RecyclerView.Adapter<BestSellingAdapter.BestSellingViewHolder>() {

    private var list: MutableList<ProductEntity> = mutableListOf()
    var onClickListener: OnClickItemAndAdd? =null

    inner class BestSellingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(productEntity: ProductEntity) {

            val price = productEntity.price
            val dec = DecimalFormat("#,###")
            val priceTL = dec.format(price)

            itemView.setOnClickListener {
                onClickListener?.onClick(productEntity)
            }

            itemView.btn_bestselling_add_cart.setOnClickListener {
                onClickListener?.onClickAdd(productEntity)
            }

            Glide.with(itemView).load(productEntity.picture).into(itemView.iv_picture_best_selling)
            itemView.tv_name_best_selling.text = productEntity.name
            itemView.tv_description_best_selling.text = productEntity.description
            itemView.tv_price_best_selling.text = "$priceTL TL"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellingViewHolder { //adapter oluşturulduğunda oluşturduğumuz ViewHolder'ın başlatılması için çağrılır
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_best_selling, parent, false)
        return BestSellingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BestSellingViewHolder, position: Int) {  //dönen verileri bağlama işlemini gerçekleştir
        holder.bind(list[position])
    }

    override fun getItemCount(): Int { //listenin eleman sayısını döndür
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataAdapter(data: List<ProductEntity>) {   //veri kaynağından veri alır
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}