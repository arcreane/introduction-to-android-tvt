package com.tvt.weatherapp.di

import com.tvt.weatherapp.data.remote.CityImageApi
import com.tvt.weatherapp.data.remote.WeatherApi
import com.tvt.weatherapp.data.repository.CityImageRepository
import com.tvt.weatherapp.data.repository.WeatherRepository
import com.tvt.weatherapp.data.repository.WeatherRepositoryImpl
import com.tvt.weatherapp.utils.Constants.OPENWEATHER_BASE_URL
import com.tvt.weatherapp.utils.Constants.UNSPLASH_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module that provides dependencies for the app.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Provide Weather API
    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(OPENWEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    // Provide Weather Repository
    @Singleton
    @Provides
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepository =
        WeatherRepositoryImpl(weatherApi)

    // Provide Unsplash API
    @Singleton
    @Provides
    fun provideCityImageApi(): CityImageApi {
        return Retrofit.Builder()
            .baseUrl(UNSPLASH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CityImageApi::class.java)
    }

    // Provide City Image Repository
    @Singleton
    @Provides
    fun provideCityImageRepository(cityImageApi: CityImageApi): CityImageRepository =
        CityImageRepository(cityImageApi)
}