package com.tvt.weatherapp.data.repository

import com.tvt.weatherapp.data.remote.CityImageApi

class CityImageRepository(private val api: CityImageApi) {
    suspend fun fetchCityImage(city: String, apiKey: String): String {
        val response = api.getCityImage("$city city", apiKey)
        return response.results.firstOrNull()?.urls?.regular ?: ""
    }
}