package com.tvt.weatherapp.data.remote

import com.tvt.weatherapp.data.model.UnsplashResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CityImageApi {
    @GET("search/photos")
    suspend fun getCityImage(
        @Query("query") city: String,
        @Query("client_id") apiKey: String
    ): UnsplashResponse
}