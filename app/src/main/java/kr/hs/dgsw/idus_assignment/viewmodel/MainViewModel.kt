package kr.hs.dgsw.idus_assignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.hs.dgsw.idus_assignment.model.response.SearchResponse
import kr.hs.dgsw.idus_assignment.model.response.WeatherItem
import kr.hs.dgsw.idus_assignment.model.response.WeatherResponse
import kr.hs.dgsw.idus_assignment.repository.WeatherRepository

class MainViewModel() : ViewModel() {
    private val repository: WeatherRepository = WeatherRepository()
    private val query : String = "Se"

    private val _searchResponse = MutableLiveData<List<SearchResponse>>()
    val searchResponse : LiveData<List<SearchResponse>> get() = _searchResponse

    private val _weatherResponse = MutableLiveData<WeatherResponse>()
    val weatherResponse: LiveData<WeatherResponse> get() = _weatherResponse

    var itemList = MutableLiveData<List<WeatherInfo>>()

    init {
        _searchResponse.value = listOf()
        searchLocal(query)
    }


    fun searchLocal(searchQuery: String) = viewModelScope.launch {
        val response = repository.getWoeid(searchQuery)
        val list = response.map {
            val weather = repository.getWeatherData(it.woeid.toInt())
            WeatherInfo(it.title, weather.consolidated_weather[0], weather.consolidated_weather[1])
        }
        itemList.value = list
    }

    data class WeatherInfo(
        val country: String,
        val today: WeatherResponse,
        val nextDay: WeatherResponse
    )

}

