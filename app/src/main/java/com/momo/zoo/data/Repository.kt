package com.momo.zoo.data

import com.momo.zoo.data.network.ZooService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class Repository(private val zooService: ZooService) {

    fun getZooData(): Single<Response<ZooData>> {
        return zooService.getZooData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}