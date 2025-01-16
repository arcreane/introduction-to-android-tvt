package com.tvt.weatherapp.domain.usecase

import android.location.Location
import com.tvt.weatherapp.data.repository.LocationRepository
import javax.inject.Inject

/**
 * Use case for getting the device's location.
 */
class GetDeviceLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(): Location? {
        print("GetDeviceLocationUseCase")
        return locationRepository.getDeviceLocation()
    }
}