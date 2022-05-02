package kr.hs.dgsw.idus_assignment.model.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("latt_long")
    val lattLong: String,
    @SerializedName("location_type")
    val locationType: String,
    val title: String,
    val woeid: String
)
