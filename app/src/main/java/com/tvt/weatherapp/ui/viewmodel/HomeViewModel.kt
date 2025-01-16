package com.tvt.weatherapp.ui.viewmodel

import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tvt.weatherapp.BuildConfig
import com.tvt.weatherapp.data.model.CurrentWeather
import com.tvt.weatherapp.data.model.WeatherForecast
import com.tvt.weatherapp.data.model.WeatherItem
import com.tvt.weatherapp.data.repository.CityImageRepository
import com.tvt.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import com.tvt.weatherapp.domain.usecase.GetDeviceLocationUseCase
import com.tvt.weatherapp.domain.usecase.GetWeatherForecastUseCase
import com.tvt.weatherapp.utils.DateTimeUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    private val getDeviceLocationUseCase: GetDeviceLocationUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val cityImageRepository: CityImageRepository // Inject CityImageRepository
) : ViewModel() {

    val locationState = mutableStateOf<Location?>(null)
    private val weatherForecastState = mutableStateOf<WeatherForecast?>(null)
    val currentWeatherState = mutableStateOf<CurrentWeather?>(null)
    val cityImageUrlState = mutableStateOf<String>("") // State for the city image URL

    /**
     * Fetch the device location.
     */
    fun getDeviceLocation() {
        viewModelScope.launch {
            val location = getDeviceLocationUseCase()
            locationState.value = location
        }
    }

    /**
     * Fetch the weather forecast based on the current location.
     */
    fun getWeatherForecast() {
        viewModelScope.launch {
            val location = locationState.value
            if (location != null) {
                val weatherForecast = getWeatherForecastUseCase(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
                weatherForecastState.value = weatherForecast
            }
        }
    }

    /**
     * Fetch the current weather based on the current location and update the city image.
     */
    fun getCurrentWeatherAndCityImage() {
        viewModelScope.launch {
            val location = locationState.value
            if (location != null) {
                // Fetch current weather
                val currentWeather = getCurrentWeatherUseCase(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
                currentWeatherState.value = currentWeather

                // Fetch city image if current weather is successfully fetched
                currentWeather?.name?.let { cityName ->
                    fetchCityImage(cityName)
                }
            }
        }
    }

    /**
     * Fetch the city image from Unsplash API.
     */
    private fun fetchCityImage(cityName: String) {
        viewModelScope.launch {
            val cityImageUrl = cityImageRepository.fetchCityImage(cityName, BuildConfig.UNSPLASH_API_KEY)
            cityImageUrlState.value = cityImageUrl // Update the state with the image URL
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentAndNextForecasts(): List<WeatherItem>? {
        val currentTime = Instant.now().epochSecond

        // Filter the list to get the forecast closest to the current time and the next 4 forecasts
        val sortedForecasts = weatherForecastState.value?.list?.sortedBy {
            DateTimeUtils.parseDtTxtToTimestamp(it.dt_txt)
        }

        val index = sortedForecasts?.indexOfFirst { DateTimeUtils.parseDtTxtToTimestamp(it.dt_txt) > currentTime }

        return if (index != -1) {
            sortedForecasts?.subList(index!!, index + 5)
        } else {
            emptyList() // No future forecasts found
        }
    }
}