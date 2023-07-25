package com.example.bookbox

import android.app.Application
import android.content.Context
import com.example.bookbox.di.viewModelModule
import com.example.bookbox.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppController: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppController)
            modules(dataModule)
            modules(viewModelModule)
        }
        INSTANCE = this
    }
    companion object{
        private var INSTANCE: AppController? = null

        @JvmStatic
        fun getInstance() : Context {
            return INSTANCE as AppController
        }
    }

    //Merge Conflict
}