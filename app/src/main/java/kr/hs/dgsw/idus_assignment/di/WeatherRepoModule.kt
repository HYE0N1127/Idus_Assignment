package kr.hs.dgsw.idus_assignment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.idus_assignment.model.service.WeatherService
import kr.hs.dgsw.idus_assignment.repository.WeatherRepository

@Module
@InstallIn(SingletonComponent::class)
object WeatherRepoModule {

    @Provides
    fun provideWeatherRepository(weatherService: WeatherService): WeatherRepository =
        WeatherRepository(weatherService)

}