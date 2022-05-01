package kr.hs.dgsw.idus_assignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.hs.dgsw.idus_assignment.model.response.SearchResponse
import kr.hs.dgsw.idus_assignment.model.response.WeatherResponse
import kr.hs.dgsw.idus_assignment.repository.WeatherRepository

class MainViewModel() : ViewModel() {
    private val repository: WeatherRepository = WeatherRepository()
    private val query : String = "Se"

    private val _searchResponse = MutableLiveData<List<SearchResponse>>()
    val searchResponse : LiveData<List<SearchResponse>> get() = _searchResponse

    private val _weatherResponse = MutableLiveData<WeatherResponse>()
    val weatherResponse: LiveData<WeatherResponse> get() = _weatherResponse

    init {
        _searchResponse.value = listOf()
        searchLocal(query)
    }

    fun searchLocal(searchQuery: String) = viewModelScope.launch {
        val response = repository.getWoeid(searchQuery)
         _searchResponse.postValue(response)
    }
}