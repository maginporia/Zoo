package com.momo.zoo.ui.home

import android.net.Uri
import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import com.momo.zoo.data.DataModel
import com.momo.zoo.pavilionItem

class EpoxyController(private val callbacks: DetailCallbacks) :
    TypedEpoxyController<List<DataModel.Result.DetailResult>?>() {
    override fun buildModels(data: List<DataModel.Result.DetailResult>?) {
        data?.forEach {
            pavilionItem {
                id(it._id)
                title(it.E_Name)
                content(it.E_Info)
                photo(Uri.parse(it.E_Pic_URL))
                imageTransition(it._id.toString() + "image")
                textTransition(it._id.toString() + "text")
                text2Transition(it._id.toString() + "text2")
                if (it.E_Memo == "") {
                    closed("無休館資訊")
                } else {
                    closed(it.E_Memo)
                }
                onItemClickListener { view ->
                    callbacks.onDetailItemClick(it, view)
                }
            }
        }
    }

    interface DetailCallbacks {
        fun onDetailItemClick(data: DataModel.Result.DetailResult, it: View)
    }
}