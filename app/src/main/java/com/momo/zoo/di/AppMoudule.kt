package com.momo.zoo.di

import com.momo.zoo.data.Repository
import com.momo.zoo.data.network.ZooService
import com.momo.zoo.ui.home.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {

    single {
        Retrofit.Builder().baseUrl("https://data.taipei/opendata/datalist/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single {
        OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()
    }
    single<ZooService> { get<Retrofit>().create(ZooService::class.java) }

    single { Repository(get()) }

    viewModel {
        HomeViewModel(get())
//        PavilionViewModel(get())
    }
}
