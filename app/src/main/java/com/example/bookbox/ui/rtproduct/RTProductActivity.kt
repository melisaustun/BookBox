package com.example.bookbox.ui.rtproduct

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookbox.R
import com.example.bookbox.listener.OnClickItemAndAdd
import com.example.bookbox.model.books.BooksData
import com.example.bookbox.model.product.ProductEntity
import com.example.bookbox.ui.activity.MainActivity
import com.example.bookbox.ui.adapter.RTBookAdapter
import com.example.bookbox.ui.detailproduct.DetailProductActivity
import com.example.bookbox.utill.Constant
import kotlinx.android.synthetic.main.activity_rtproduct.*
import org.koin.android.viewmodel.ext.android.viewModel

class RTProductActivity : AppCompatActivity() {

    private val rtbookAdapter: RTBookAdapter by lazy {
        RTBookAdapter()
    }

    private val viewModel: RTProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rtproduct)

        //actionbar
        supportActionBar?.hide()

        observeRTBook()
        setListRT()
        backToMainActivity3()
        viewModel.showDataRTBook()
    }

    private fun observeRTBook() {
        viewModel.rtbook.observe(this) {
            rtbookAdapter.setDataAdapter(it)
        }
    }

    private fun setListRT() {
        rv_rtbook.setHasFixedSize(true)
        rv_rtbook.adapter = rtbookAdapter
        rv_rtbook.layoutManager = GridLayoutManager(this, 2)
        rtbookAdapter.onClickListener = object : OnClickItemAndAdd {
            override fun onClick(productEntity: ProductEntity) {
                val intent = Intent(this@RTProductActivity, DetailProductActivity::class.java)
                intent.putExtra(Constant.DATA, productEntity)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                startActivity(intent)
            }

            override fun onClickAdd(productEntity: ProductEntity) {
                addProductToCart(productEntity)
            }
        }
    }

    private fun backToMainActivity3() {
        btn_back3.setOnClickListener {
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