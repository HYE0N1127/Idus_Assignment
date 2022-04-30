package kr.hs.dgsw.idus_assignment.model

import kr.hs.dgsw.idus_assignment.model.service.WeatherService
import kr.hs.dgsw.idus_assignment.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Server {
    val okHttpClient = OkHttpClient().newBuilder()
        .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(Constants.BASE_URL)
        .build()

    val weatherService: WeatherService = retrofit.create(WeatherService::class.java)
}