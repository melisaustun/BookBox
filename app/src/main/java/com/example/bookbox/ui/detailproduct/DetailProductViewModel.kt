package com.example.bookbox.ui.detailproduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookbox.data.Repository
import com.example.bookbox.model.product.ProductEntity
import com.example.bookbox.utill.ProductSavedType

class DetailProductViewModel(private val repository: Repository) : ViewModel() {

    private val _detailProduct = MutableLiveData<List<ProductEntity>>()
    val detailProduct: LiveData<List<ProductEntity>> = _detailProduct

    private val _isfavorited = MutableLiveData<Boolean>()
    val isFavorited: LiveData<Boolean> = _isfavorited

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private var dataFavorite: ArrayList<ProductEntity> = ArrayList()

    fun loadDataDetail() {
        dataFavorite.clear()
        dataFavorite.addAll(repository.getAllDb(ProductSavedType.FAV))
        _detailProduct.postValue(dataFavorite)
    }

    fun saveProduct(productEntity: ProductEntity) {
        repository.addToFav(productEntity)
    }

    fun removeProduct(productEntity: ProductEntity) {
         repository.removeProductFav(productEntity.id, ProductSavedType.FAV)
    }

    fun checkProduct(productEntity: ProductEntity) {
        _isfavorited.postValue(repository.checkProduct(productEntity.id))
    }

    fun addToChar(productEntity: ProductEntity, cart: Int) {
        repository.addToCart(productEntity)
    }


}