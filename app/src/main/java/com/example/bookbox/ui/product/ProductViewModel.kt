package com.example.bookbox.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookbox.data.Repository
import com.example.bookbox.model.product.ProductEntity
import com.example.bookbox.utill.ProductSavedType
import io.reactivex.disposables.CompositeDisposable

class ProductViewModel(private val repository : Repository): ViewModel() {

    private val _book = MutableLiveData<ArrayList<ProductEntity>>()
    val book: LiveData<ArrayList<ProductEntity>> = _book

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    fun showDataBook() {
        val beveragesDisposable = repository.getBook()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _book.postValue(it) },
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