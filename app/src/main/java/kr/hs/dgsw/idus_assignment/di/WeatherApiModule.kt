package kr.hs.dgsw.idus_assignment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.idus_assignment.model.service.WeatherService
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object WeatherApiModule {

    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)

}