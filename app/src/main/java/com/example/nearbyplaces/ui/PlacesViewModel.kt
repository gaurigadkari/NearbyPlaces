package com.example.nearbyplaces.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nearbyplaces.model.Place
import com.example.nearbyplaces.network.PlacesApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Gauri Gadkari on 11/8/23.
 */
private val location = "37.423,-122.083"
private val radius = 5000  // 5 kilometers
private val type = "restaurant"
// Add your own API_KEY here
private val apiKey = ""

sealed class PlacesUiState {
    data class Success(val places: List<Place>) : PlacesUiState()
    data class Error(val error: String) : PlacesUiState()
    object Loading : PlacesUiState()
}

class PlacesViewModel : ViewModel() {
    var placesUiState: PlacesUiState by mutableStateOf(PlacesUiState.Loading)

    init {

        viewModelScope.launch {
            placesUiState = PlacesUiState.Loading
            placesUiState = try { PlacesUiState.Success(
                    PlacesApi.retrofitService.getPlaces(location, radius, type, apiKey).results
            ) } catch (e: IOException) {
                PlacesUiState.Error(e.message.toString())
            } catch (e: HttpException) {
                PlacesUiState.Error(e.message.toString())
            }
        }
    }
}
// you can use log statements to see your network call before connecting with UI
//Log.d("Debug", PlacesApi.retrofitService.getPlaces(location, radius, type, apiKey).toString())