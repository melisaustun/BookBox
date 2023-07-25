package com.example.bookbox.listener

import com.example.bookbox.model.product.ProductEntity


interface OnSearchListener {
    fun onSearch(productEntity: ProductEntity)
}