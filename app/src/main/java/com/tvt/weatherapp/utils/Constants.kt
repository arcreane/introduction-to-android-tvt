package com.tvt.weatherapp.utils

import androidx.compose.ui.unit.dp
import com.tvt.weatherapp.BuildConfig

object Constants {
    const val OPENWEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val UNSPLASH_BASE_URL = "https://api.unsplash.com/"

    const val OPENWEATHER_API_KEY = BuildConfig.OPENWEATHER_API_KEY
    const val UNSPLASH_API_KEY = BuildConfig.UNSPLASH_API_KEY

    val SCREEN_PADDING = 16.dp
}