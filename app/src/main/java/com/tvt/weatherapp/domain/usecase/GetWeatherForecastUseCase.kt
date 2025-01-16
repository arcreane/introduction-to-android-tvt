package com.tvt.weatherapp.domain.usecase

import com.tvt.weatherapp.data.repository.WeatherRepository
import com.tvt.weatherapp.data.model.WeatherForecast
import javax.inject.Inject

/**
 * Represents the use case to get the weather forecast.
 *
 * @param weatherRepository The repository for weather data.
 */
class GetWeatherForecastUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(latitude: Double, longitude: Double): WeatherForecast {
        return weatherRepository.getWeatherForecast(latitude, longitude)
    }
}