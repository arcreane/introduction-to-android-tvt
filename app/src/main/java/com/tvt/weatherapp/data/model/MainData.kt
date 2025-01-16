package com.tvt.weatherapp.data.model

data class MainData(
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double
)
