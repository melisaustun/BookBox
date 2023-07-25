package com.example.bookbox.ui.product

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookbox.R
import com.example.bookbox.ui.adapter.BookAdapter
import com.example.bookbox.listener.OnClickItemAndAdd
import com.example.bookbox.model.books.BooksData
import com.example.bookbox.model.product.ProductEntity
import com.example.bookbox.ui.activity.MainActivity
import com.example.bookbox.ui.detailproduct.DetailProductActivity
import com.example.bookbox.utill.Constant
import kotlinx.android.synthetic.main.activity_product.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProductActivity : AppCompatActivity() {

    private val bookAdapter: BookAdapter by lazy {
        BookAdapter()
    }

    private val viewModel: ProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        //actionbar
        supportActionBar?.hide()

        observeBook()
        setListOffers()
        backToMainActivity()
        viewModel.showDataBook()
    }

    private fun observeBook() {
        viewModel.book.observe(this) {
            bookAdapter.setDataAdapter(it)
        }
    }

    private fun setListOffers() {
        rv_book.setHasFixedSize(true)
        rv_book.adapter = bookAdapter
        rv_book.layoutManager = GridLayoutManager(this, 2)
        bookAdapter.onClickListener = object : OnClickItemAndAdd {
            override fun onClick(productEntity: ProductEntity) {
                val intent = Intent(this@ProductActivity, DetailProductActivity::class.java)
                intent.putExtra(Constant.DATA, productEntity)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                startActivity(intent)
            }

            override fun onClickAdd(productEntity: ProductEntity) {
                addProductToCart(productEntity)
            }
        }
    }

    private fun backToMainActivity() {
        btn_back.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }

    private fun addProductToCart(productEntity: ProductEntity) {
        if (productEntity.qty == 0) {
            viewModel.addToChar(productEntity)
            Toast.makeText(applicationContext, "Sepete eklendi", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.removeProduct(productEntity)
            Toast.makeText(applicationContext, "Sepetten kaldırıldı.", Toast.LENGTH_SHORT).show()
        }
    }
}