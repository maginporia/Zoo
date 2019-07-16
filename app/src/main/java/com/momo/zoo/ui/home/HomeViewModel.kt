package com.momo.zoo.ui.home

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.momo.zoo.data.Repository
import com.momo.zoo.data.ZooData
import com.momo.zoo.data.network.HttpResult
import io.reactivex.rxkotlin.subscribeBy
import retrofit2.Response

class HomeViewModel(private val repository: Repository) : ViewModel() {
    val progress = MutableLiveData<Int>()
    val zooData = MutableLiveData<HttpResult<Response<ZooData>>>().apply {
        repository.getZooData()
            .subscribeBy(  // named arguments for lambda Subscribers
                onError = {
                    value = HttpResult.Error(it)
                    progress.value = View.GONE
                },
                onSuccess = {
                    value = HttpResult.Success(it)
                    progress.value = View.GONE
                }
            )
    }
}