package com.example.bookbox.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.bookbox.R
import com.example.bookbox.listener.OnClickItem
import com.example.bookbox.model.product.ProductEntity
import kotlinx.android.synthetic.main.item_favorite.view.*
import java.text.DecimalFormat

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val list: MutableList<ProductEntity> = mutableListOf()
    var onClickListener: OnClickItem? =null

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(productEntity: ProductEntity) {

            val price = productEntity.price
            val dec = DecimalFormat("#,###")
            val priceTL = dec.format(price)

            itemView.setOnClickListener {
                onClickListener?.onClick(productEntity)
            }
            //productEntity tablosunda tanımlanan nesneleri çeker
            Glide.with(itemView)
                .load(productEntity.picture)
                .transition(DrawableTransitionOptions.withCrossFade())
                .fitCenter()
                .into(itemView.iv_picture_favorite)
            itemView.tv_name_favorite.text = productEntity.name
            itemView.tv_description_favorite.text = productEntity.description
            itemView.tv_price_favorite.text = "$priceTL TL"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder { //adapter oluşturulduğunda oluşturduğumuz ViewHolder'ın başlatılması için çağrılır
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {//dönen verileri bağlama işlemini gerçekleştir
        holder.bind(list[position])
    }

    override fun getItemCount(): Int { //listenin eleman sayısını döndür
        return list.size
    }

    fun deleteItem(pos:Int) {
        list.removeAt(pos)
        notifyItemRemoved(pos)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataAdapter(data: List<ProductEntity>) { //veri kaynağından veri alır
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}