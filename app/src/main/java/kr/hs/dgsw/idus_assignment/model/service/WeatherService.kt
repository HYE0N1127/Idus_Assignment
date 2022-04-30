package kr.hs.dgsw.idus_assignment.model.service

import kr.hs.dgsw.idus_assignment.model.response.SearchResponse
import kr.hs.dgsw.idus_assignment.model.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("location/search/")
    suspend fun getWeatherInfoByName(
        @Query("query") query : String
    ) : List<SearchResponse>

    @GET("location/{woeid}")
    suspend fun getWeatherInfo(
        @Path("woeid") woeid: String): WeatherResponse
    )
}