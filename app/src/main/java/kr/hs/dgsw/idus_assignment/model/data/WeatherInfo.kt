package kr.hs.dgsw.idus_assignment.model.data

import kr.hs.dgsw.idus_assignment.model.response.WeatherResponse

data class WeatherInfo(
    val country: String,
    val today: WeatherResponse,
    val nextDay: WeatherResponse
)