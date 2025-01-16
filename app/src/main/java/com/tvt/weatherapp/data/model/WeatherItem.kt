package com.tvt.weatherapp.data.model

data class WeatherItem (
    val main: MainData,
    val weather: List<WeatherCondition>,
    val wind: Wind,
    val dt_txt: String // Date and time in text format
)