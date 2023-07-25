package com.example.bookbox.ui.cart

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bookbox.R
import com.example.bookbox.ui.adapter.CartAdapter
import com.example.bookbox.listener.OnClickItemAddRemove
import com.example.bookbox.listener.OnTotalChange
import com.example.bookbox.model.product.ProductEntity
import com.example.bookbox.ui.detailproduct.DetailProductActivity
import com.example.bookbox.utill.Constant
import com.example.bookbox.utill.ProductSavedType
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_cart.btn_favorite
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DecimalFormat


class CartFragment : Fragment(R.layout.fragment_cart) {

    private val viewModel: CartViewModel by viewModel()

    private val cartAdapter: CartAdapter by lazy {
        CartAdapter(object : OnTotalChange {
            @SuppressLint("SetTextI18n")
            override fun onTotalChange(total: Int) {
                val dec = DecimalFormat("#,###")
                val priceTL = dec.format(total)

                tv_total_price.text = " $priceTL TL"
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_favorite.setOnClickListener {
            startActivity(Intent(activity, BuyBooks::class.java))
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        btn_rental.setOnClickListener {
            startActivity(Intent(activity, RentBooks::class.java))
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        observeCart()
        setListCart()
        viewModel.loadDataCart()

    }

    private fun observeCart() {
        viewModel.cartProduct.observe(viewLifecycleOwner) {
            cartAdapter.setDataAdapter(it)
        }
    }

    private fun setListCart() {

        rv_cart.setHasFixedSize(true)
        rv_cart.adapter = cartAdapter
        cartAdapter.onClickListener = object : OnClickItemAddRemove {
            override fun onClick(productEntity: ProductEntity) {
                val intent = Intent(activity, DetailProductActivity::class.java)
                intent.putExtra(Constant.DATA, productEntity)
                requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                startActivity(intent)
            }

            override fun onClickAdd(productEntity: ProductEntity) {
                addQtyProduct(productEntity)
            }

            override fun onClickSubstract(productEntity: ProductEntity) {
                substractQtyProduct(productEntity)
            }

            override fun onClickRemove(productEntity: ProductEntity) {
                removeFromCart(productEntity)
            }
        }

    }

    private fun addQtyProduct(productEntity: ProductEntity) {
        viewModel.addProduct(productEntity)
        viewModel.loadDataCart()
    }

    private fun substractQtyProduct(productEntity: ProductEntity) {
        viewModel.subtractProduct(productEntity)
        viewModel.loadDataCart()
    }

    private fun removeFromCart(productEntity: ProductEntity) {
        viewModel.removeProduct(productEntity, ProductSavedType.CART)
        Toast.makeText(activity, "Sepetten kaldırıldı", Toast.LENGTH_SHORT).show()
        viewModel.loadDataCart()
    }
}