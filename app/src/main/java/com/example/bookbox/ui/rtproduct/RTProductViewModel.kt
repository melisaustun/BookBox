package com.example.bookbox.ui.rtproduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookbox.data.Repository
import com.example.bookbox.model.product.ProductEntity
import com.example.bookbox.utill.ProductSavedType
import io.reactivex.disposables.CompositeDisposable

class RTProductViewModel(private val repository : Repository): ViewModel() {

    private val _rtbook = MutableLiveData<ArrayList<ProductEntity>>()
    val rtbook: LiveData<ArrayList<ProductEntity>> = _rtbook

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    fun showDataRTBook() {
        val beveragesDisposable = repository.getRentalAll()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _rtbook.postValue(it) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(beveragesDisposable)
    }

    fun addToChar(productEntity: ProductEntity) {
        repository.addToCart(productEntity)
    }

    fun removeProduct(productEntity: ProductEntity) {
        repository.removeProductCart(productEntity.id, ProductSavedType.CART)
    }
}