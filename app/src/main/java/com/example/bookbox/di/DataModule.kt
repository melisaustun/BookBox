package com.example.bookbox.di

import com.example.bookbox.data.DummyDataSource
import com.example.bookbox.data.Repository
import com.example.bookbox.model.DataBase
import org.koin.dsl.module

val dataModule = module {

    single { DataBase.getInstance() }
    factory { DummyDataSource() }
    single { Repository(get(), get()) }

}