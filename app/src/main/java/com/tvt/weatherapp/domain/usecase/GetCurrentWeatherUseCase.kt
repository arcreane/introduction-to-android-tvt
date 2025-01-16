package com.tvt.weatherapp.domain.usecase

import com.tvt.weatherapp.data.repository.WeatherRepository
import com.tvt.weatherapp.data.model.CurrentWeather
import javax.inject.Inject

/**
 * Represents the use case to get the current weather.
 *
 * @param weatherRepository The repository for weather data.
 */
class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(latitude: Double, longitude: Double): CurrentWeather {
        return weatherRepository.getCurrentWeather(latitude, longitude)
    }
}