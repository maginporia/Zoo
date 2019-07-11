package com.momo.zoo.binding

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object CustomSetter {

    @BindingAdapter("app:setImageUrl")
    @JvmStatic
    fun loadImageUrl(view: ImageView, uri: Uri?) {
        if (uri.toString() != "") {
            Glide
                .with(view.context)
                .load(uri)
//                .error(R.drawable.nopic)
                .centerCrop()
                .into(view)
        } else {
//            Glide
//                .with(view.context)
//                .load(R.drawable.nopic)
//                .centerCrop()
//                .into(view)
        }
    }
}