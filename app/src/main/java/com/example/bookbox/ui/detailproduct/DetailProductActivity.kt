package com.example.bookbox.ui.detailproduct

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.bookbox.R
import com.example.bookbox.model.product.ProductEntity
import com.example.bookbox.utill.Constant
import com.example.bookbox.utill.ProductSavedType
import kotlinx.android.synthetic.main.activity_detail_product.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DecimalFormat

class DetailProductActivity : AppCompatActivity() {

    private val viewModel: DetailProductViewModel by viewModel()

    private val product by lazy {
        intent.getParcelableExtra<ProductEntity>(Constant.DATA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        //actionbar
        supportActionBar?.hide()

        observeIsFavorited()
        loadDataDetail(product!!)
        addProductToCart(product!!, ProductSavedType.CART)

        viewModel.checkProduct(product!!)
        viewModel.loadDataDetail()
    }


    @SuppressLint("SetTextI18n")
    private fun loadDataDetail(productEntity: ProductEntity) {
        val price = productEntity.price
        val dec = DecimalFormat("#,###")
        val priceTL = dec.format(price)

        Glide.with(this).load(productEntity.picture)
            .transition(DrawableTransitionOptions.withCrossFade()).into(iv_picture_detail)
        tv_name_detail.text = productEntity.name
        tv_description_detail.text = productEntity.description
        tv_price_detail.text = " $priceTL TL"
    }

    private fun observeIsFavorited() {
        viewModel.isFavorited.observe(this) {
            btn_favorite.isChecked = it
            addFavoriteMovie()
        }
    }

    private fun addFavoriteMovie(){
        btn_favorite.setOnCheckedChangeListener { checkBox, isChecked ->
            if (isChecked){
                viewModel.saveProduct(product!!)
                Toast.makeText(this, "Favorilere eklendi", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.removeProduct(product!!)
                Toast.makeText(this, "Favorilerden kaldırıldı", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addProductToCart(productEntity: ProductEntity, cart: Int) {
        btn_detail_addtocart.setOnClickListener {
                viewModel.addToChar(productEntity, ProductSavedType.CART)
                Toast.makeText(this, "Sepete eklendi", Toast.LENGTH_SHORT).show()
        }
    }

}