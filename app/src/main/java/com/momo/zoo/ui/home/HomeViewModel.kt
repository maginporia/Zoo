package com.momo.zoo.ui.home

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.momo.zoo.data.DataModel
import com.momo.zoo.data.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val repository: Repository) : ViewModel() {
    val progress = MutableLiveData<Int>()
    val zooData = MutableLiveData<DataModel>().apply {
        repository.getZooData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(  // named arguments for lambda Subscribers
                onError = { it.printStackTrace() },
                onSuccess = {
                    if (it.isSuccessful) {
                        value = it.body()
                        progress.value = View.GONE
                    }
                }
            )
    }
}