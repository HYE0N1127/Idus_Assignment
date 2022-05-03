package kr.hs.dgsw.idus_assignment.repository

import kr.hs.dgsw.idus_assignment.model.response.SearchResponse
import kr.hs.dgsw.idus_assignment.model.response.WeatherItem
import kr.hs.dgsw.idus_assignment.model.service.WeatherService
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val service: WeatherService) {

    suspend fun getWoeid(searchQuery: String): List<SearchResponse> =
        service.getWeatherInfoByName(searchQuery)

    suspend fun getWeatherData(woeid: Int): WeatherItem =
        service.getWeatherInfo(woeid)

}