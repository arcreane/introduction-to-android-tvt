package com.tvt.weatherapp.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import java.util.Locale
import com.tvt.weatherapp.utils.DateTimeUtils


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainWeatherCard(
    currentTemp: Int,
    feelsLikeTemp: Int,
    weatherType: String,
    location: String,
    cityImageUrl: String // Accept city image URL
) {
    val currentDate = DateTimeUtils.getCurrentDate()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp / 1.2f
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenWidth),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(
                alpha = 0.5f
            ),
        ),
    ) {
        Box {
            // Load city image dynamically using Coil
            Image(
                painter = rememberAsyncImagePainter(cityImageUrl),
                contentDescription = "City Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.3f))
                    .fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = currentDate,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(48.dp))
                Box(
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "$currentTemp°C",
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.displayLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Feels like $feelsLikeTemp°C",
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = weatherType.uppercase(Locale.getDefault()),
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.weight(1.0f))
                        Text(
                            text = location,
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}