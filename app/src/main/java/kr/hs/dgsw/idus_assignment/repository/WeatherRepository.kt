package kr.hs.dgsw.idus_assignment.repository

import androidx.lifecycle.LifecycleCoroutineScope
import kr.hs.dgsw.idus_assignment.model.Server
import kr.hs.dgsw.idus_assignment.model.response.SearchResponse
import kr.hs.dgsw.idus_assignment.model.response.WeatherItem
import kr.hs.dgsw.idus_assignment.model.response.WeatherResponse
import kr.hs.dgsw.idus_assignment.model.service.WeatherService

class WeatherRepository {
    private val weatherService = Server.weatherService

    suspend fun getWoeid(searchQuery: String): List<SearchResponse> =
        weatherService.getWeatherInfoByName(searchQuery)

    suspend fun getWeatherData(woeid: Int): WeatherItem =
        weatherService.getWeatherInfo(woeid)

}