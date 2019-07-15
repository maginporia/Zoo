package com.momo.zoo.data.network

import com.momo.zoo.data.ZooData
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ZooService {
    @GET("apiAccess?scope=resourceAquire&rid=5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a")
    fun getZooData(): Single<Response<ZooData>>
}