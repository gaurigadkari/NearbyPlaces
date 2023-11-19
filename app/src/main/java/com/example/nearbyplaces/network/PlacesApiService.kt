package com.example.nearbyplaces.network

import com.example.nearbyplaces.model.Response
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Gauri Gadkari on 11/8/23.
 */

private const val BASE_URL = "https://maps.googleapis.com/maps/api/place/"

interface PlacesApiService {
    @GET("nearbysearch/json")
    suspend fun getPlaces(
        @Query("location") location: String,
        @Query("radius") radius: Int,
        @Query("type") type: String,
        @Query("key") apiKey: String
    ): Response
}

private val json = Json {
    ignoreUnknownKeys = true
}

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()

object PlacesApi {
    val retrofitService : PlacesApiService by lazy {
        retrofit.create(PlacesApiService::class.java)
    }
}