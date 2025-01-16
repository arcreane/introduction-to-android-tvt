package com.tvt.weatherapp.data.repository

import com.tvt.weatherapp.data.remote.WeatherApi
import com.tvt.weatherapp.data.model.CurrentWeather
import com.tvt.weatherapp.data.model.WeatherForecast
import javax.inject.Inject

/**
 * Repository for weather data.
 */
interface WeatherRepository {
    /**
     * Get the weather forecast for a given latitude and longitude
     */
    suspend fun getWeatherForecast(latitude: Double, longitude: Double): WeatherForecast

    /**
     * Get the current weather for a given latitude and longitude
     */
    suspend fun getCurrentWeather(latitude: Double, longitude: Double): CurrentWeather
}