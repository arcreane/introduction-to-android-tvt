package com.tvt.weatherapp.data.remote

import com.tvt.weatherapp.data.model.CurrentWeather
import com.tvt.weatherapp.data.model.WeatherForecast
import com.tvt.weatherapp.utils.Constants.OPENWEATHER_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for defining the OpenWeatherMap API endpoints.
 */
interface WeatherApi {

    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = OPENWEATHER_API_KEY,
        @Query("units") units: String = "metric"
    ): WeatherForecast

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = OPENWEATHER_API_KEY,
        @Query("units") units: String = "metric"
    ): CurrentWeather
}