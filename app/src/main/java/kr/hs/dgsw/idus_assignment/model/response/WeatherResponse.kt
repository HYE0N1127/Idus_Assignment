package kr.hs.dgsw.idus_assignment.model.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("weather_state_name")
    val weatherStateName: String,      // 날씨 요약 (ex. 맑음, 흐림... etc)
    @SerializedName("weather_state_abbr")
    val weatherIcon: String,       // 아이콘 이미지 (URL 형태로 날아옴)
    @SerializedName("the_temp")
    val temperature: Int,      // 온도
    val humidity: Float,        // 습도
)