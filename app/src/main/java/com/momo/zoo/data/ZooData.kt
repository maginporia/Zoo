package com.momo.zoo.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ZooData(
    val result: Result
) {
    data class Result(
        val limit: Int, // 1000
        val offset: Int, // 0
        val count: Int, // 18
        val sort: String,
        val results: List<DetailResult>
    ) {
        @Parcelize
        data class DetailResult(
            @SerializedName("E_Pic_URL")
            val ePicURL: String, // http://www.zoo.gov.tw/iTAP/05_Exhibit/18_TheStage.jpg
            @SerializedName("E_Geo")
            val eGeo: String, // MULTIPOINT ((121.5826075 24.9987238))
            @SerializedName("E_Info")
            val eInfo: String, // 為了讓更多的市民貼近、瞭解大自然，同時寓教於樂，兒童動物區內的特展區「生命驛站」，希望透過農田環境、動物標本和農具展示，以及自導式互動遊戲，呈現大自然萬物的生命循環歷程，讓參觀者感受生命與環境間的重要關聯。
            @SerializedName("E_no")
            val eNo: String, // 18
            @SerializedName("E_Category")
            val eCategory: String, // 特展區
            @SerializedName("E_Name")
            val eName: String, // 生命驛站
            @SerializedName("E_Memo")
            val eMemo: String, // 每週六、日13:30-15:30開放參觀
            @SerializedName("_id")
            val id: Int, // 18
            @SerializedName("E_URL")
            val eURL: String // https://www.zoo.gov.taipei/cp.aspx?n=274AFEA1B85B9D7B
        ) : Parcelable
    }
}