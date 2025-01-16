package com.tvt.weatherapp.data.model

data class UnsplashResponse(
    val results: List<ImageResult>
)

data class ImageResult(
    val urls: Urls
)

data class Urls(
    val regular: String
)
