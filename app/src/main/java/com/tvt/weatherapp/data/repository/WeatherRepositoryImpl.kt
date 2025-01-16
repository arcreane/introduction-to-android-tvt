package com.tvt.weatherapp.data.repository

import com.tvt.weatherapp.data.remote.WeatherApi
import com.tvt.weatherapp.data.model.CurrentWeather
import com.tvt.weatherapp.data.model.WeatherForecast
import javax.inject.Inject

/**
 * Repository for weather data.
 */
class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
): WeatherRepository {
    override suspend fun getWeatherForecast(latitude: Double, longitude: Double): WeatherForecast {
        return weatherApi.getWeatherForecast(latitude, longitude)
    }

    override suspend fun getCurrentWeather(latitude: Double, longitude: Double): CurrentWeather {
        return weatherApi.getCurrentWeather(latitude, longitude)
    }
}