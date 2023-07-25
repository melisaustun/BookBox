package com.example.bookbox.ui.bsproduct

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
import com.example.bookbox.ui.adapter.BSBookAdapter
import com.example.bookbox.ui.detailproduct.DetailProductActivity
import com.example.bookbox.utill.Constant
import kotlinx.android.synthetic.main.activity_bsproduct.*
import org.koin.android.viewmodel.ext.android.viewModel

class BSProductActivity : AppCompatActivity() {

    private val bsbookAdapter: BSBookAdapter by lazy {
        BSBookAdapter()
    }

    private val viewModel: BSProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bsproduct)

        //actionbar
        supportActionBar?.hide()

        observeBSBook()
        setListbs()
        backToMainActivity2()
        viewModel.showDataBSBook()
    }

    private fun observeBSBook() {
        viewModel.bsbook.observe(this) {
            bsbookAdapter.setDataAdapter(it)
        }
    }

    private fun setListbs() {
        rv_bsbook.setHasFixedSize(true)
        rv_bsbook.adapter = bsbookAdapter
        rv_bsbook.layoutManager = GridLayoutManager(this, 2)
        bsbookAdapter.onClickListener = object : OnClickItemAndAdd {
            override fun onClick(productEntity: ProductEntity) {
                val intent = Intent(this@BSProductActivity, DetailProductActivity::class.java)
                intent.putExtra(Constant.DATA, productEntity)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                startActivity(intent)
            }

            override fun onClickAdd(productEntity: ProductEntity) {
                addProductToCart(productEntity)
            }
        }
    }

    private fun backToMainActivity2() {
        btn_back2.setOnClickListener {
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