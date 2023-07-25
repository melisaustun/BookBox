package com.example.bookbox.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookbox.model.product.ProductEntity
import com.example.bookbox.data.Repository
import com.example.bookbox.model.books.BooksData
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(private val repository : Repository) : ViewModel() {

    private val _rental = MutableLiveData<ArrayList<ProductEntity>>()
    val rental: LiveData<ArrayList<ProductEntity>> = _rental

    private val _bestsSelling = MutableLiveData<ArrayList<ProductEntity>>()
    val bestsSelling: LiveData<ArrayList<ProductEntity>> = _bestsSelling

    private val _category = MutableLiveData<ArrayList<ProductEntity>>()
    val category: LiveData<ArrayList<ProductEntity>> = _category

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }


    fun showDataRental() {
        val rentalDisposable = repository.getRental()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _rental.postValue(it) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(rentalDisposable)
    }

    fun showDataCategory() {
        val rentalDisposable = repository.getCategory()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _category.postValue(it) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(rentalDisposable)
    }

    fun showDataBestSelling() {
        val bestSellingDisposable = repository.getBestSelling()
            .doOnSubscribe { }
            .doFinally { }
            .subscribe({ _bestsSelling.postValue(it) },
            { _errorMessage.postValue(it.localizedMessage)})
        compositeDisposable.add(bestSellingDisposable)
    }

    fun addToChar(productEntity: ProductEntity, cart: Int) {
        repository.addToCart(productEntity)
    }


}