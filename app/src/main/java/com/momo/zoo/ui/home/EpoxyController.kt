package com.momo.zoo.ui.home

import android.net.Uri
import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import com.momo.zoo.data.ZooData
import com.momo.zoo.pavilionItem

class EpoxyController(private val callbacks: DetailCallbacks) :
    TypedEpoxyController<List<ZooData.Result.DetailResult>?>() {
    override fun buildModels(data: List<ZooData.Result.DetailResult>?) {
        data?.forEach {
            pavilionItem {
                id(it.id)
                title(it.eName)
                content(it.eInfo)
                photo(Uri.parse(it.ePicURL))
                imageTransition(it.id.toString() + "image")
                textTransition(it.id.toString() + "text")
                text2Transition(it.id.toString() + "text2")
                if (it.eMemo == "") {
                    closed("無休館資訊")
                } else {
                    closed(it.eMemo)
                }
                onItemClickListener { view ->
                    callbacks.onDetailItemClick(it, view)
                }
            }
        }
    }

    interface DetailCallbacks {
        fun onDetailItemClick(data: ZooData.Result.DetailResult, it: View)
    }
}