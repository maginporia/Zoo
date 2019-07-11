package com.momo.zoo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class DataModel(
    val result: Result
) {
    data class Result(
        val count: Int,
        val limit: Int,
        val offset: Int,
        val results: List<DetailResult>,
        val sort: String
    ) {
        @Parcelize
        data class DetailResult(
            val E_Category: String,
            val E_Geo: String,
            val E_Info: String,
            val E_Memo: String,
            val E_Name: String,
            val E_Pic_URL: String,
            val E_URL: String,
            val E_no: String,
            val _id: Int
        ) : Parcelable
    }
}