package com.example.nearbyplaces.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nearbyplaces.R
import com.example.nearbyplaces.model.Place
import com.example.nearbyplaces.ui.theme.NearbyPlacesTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NearbyPlacesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val placesViewModel: PlacesViewModel = viewModel()
                    PlacesApp(
                        placesUIState = placesViewModel.placesUiState,
                        )
                }
            }
        }
    }
}

@Composable
fun PlacesApp(placesUIState: PlacesUiState
) {
    when (placesUIState) {
        is PlacesUiState.Success -> {
            PlacesList(placesUIState.places)
        }
        is PlacesUiState.Loading -> {
            // TODO show spinner
        }
        is PlacesUiState.Error -> {
            // TODO add error to logs
        }
    }
}

@Composable
fun PlacesList(placesList: List<Place>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(placesList) { place ->
            NearbyPlaceCard(place = place)
        }
    }
}

@Composable
fun NearbyPlaceCard(place: Place, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(4.dp)) {
        Column {
            Text(
                text = place.name,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineLarge
            )
            Row (horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxSize()
                ) {
                Text(
                    text = stringResource(R.string.rating) + place.rating.toString(),
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = if (place.openingHours?.openNow == true) {
                        stringResource(R.string.open_now)
                    } else {
                        stringResource(
                            R.string.closed
                        )
                    },
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}