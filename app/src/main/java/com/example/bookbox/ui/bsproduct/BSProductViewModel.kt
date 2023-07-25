package com.example.bookbox.ui.bsproduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookbox.data.Repository
import com.example.bookbox.model.product.ProductEntity
import com.example.bookbox.utill.ProductSavedType
import io.reactivex.disposables.CompositeDisposable

class BSProductViewModel(private val repository : Repository): ViewModel() {

    private val _bsbook = MutableLiveData<ArrayList<ProductEntity>>()
    val bsbook: LiveData<ArrayList<ProductEntity>> = _bsbook

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    fun showDataBSBook() {
        val beveragesDisposable = repository.getBestSellingAll()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _bsbook.postValue(it) },
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