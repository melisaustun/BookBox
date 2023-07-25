package com.example.bookbox.di

import com.example.bookbox.ui.bsproduct.BSProductViewModel
import com.example.bookbox.ui.cart.CartViewModel
import com.example.bookbox.ui.detailproduct.DetailProductViewModel
import com.example.bookbox.ui.explore.ExploreViewModel
import com.example.bookbox.ui.favorite.FavoriteViewModel
import com.example.bookbox.ui.product.ProductViewModel
import com.example.bookbox.ui.home.HomeViewModel
import com.example.bookbox.ui.rtproduct.RTProductViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { ProductViewModel(get()) }
    viewModel { DetailProductViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { ExploreViewModel(get()) }
    viewModel { CartViewModel(get()) }
    viewModel { BSProductViewModel(get()) }
    viewModel { RTProductViewModel(get()) }


}