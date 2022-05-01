package kr.hs.dgsw.idus_assignment.model.response

import com.google.gson.annotations.SerializedName

data class WeatherItem(
    val consolidated_weather: List<WeatherResponse>,
)

data class WeatherResponse(
    @SerializedName("applicable_date")
    val applicableDate: String = "",
    val humidity: Int = 0,
    @SerializedName("the_temp")
    val theTemp: Double = 0.0,
    @SerializedName("weather_state_abbr")
    val weatherStateAbbr: String = "",
    @SerializedName("weather_state_name")
    val weatherStateName: String = "",
)



