package kr.hs.dgsw.idus_assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kr.hs.dgsw.idus_assignment.model.data.WeatherInfo
import kr.hs.dgsw.idus_assignment.repository.WeatherRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {

    val query: String = "Se"

    private val _itemList = MutableLiveData<List<WeatherInfo>>()
    val itemList: LiveData<List<WeatherInfo>> get() = _itemList

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        searchWeatherData(query)
    }

    fun searchWeatherData(searchQuery: String) = viewModelScope.launch {
        _isLoading.postValue(true)

        val response = repository.getWoeid(searchQuery)
        val list = response.map {
            async {
                val weather = repository.getWeatherData(it.woeid.toInt())
                WeatherInfo(it.title,
                    weather.consolidated_weather[0],
                    weather.consolidated_weather[1])
            }
        }.map {
            it.await()
        }
        _itemList.postValue(list)
        _isLoading.postValue(false)
    }
}

