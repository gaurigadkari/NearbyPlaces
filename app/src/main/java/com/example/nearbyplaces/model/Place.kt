package com.example.nearbyplaces.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Created by Gauri Gadkari on 11/6/23.
 */

@Serializable
data class Place(
    @SerialName("business_status")
    val businessStatus: String,
    val geometry: Geometry,
    val icon: String,
    @SerialName("icon_background_color")
    val iconBackgroundColor: String,
    @SerialName("icon_mask_base_uri")
    val iconMaskBaseUri: String,
    val name: String,
    @SerialName("opening_hours")
    val openingHours: OpeningHours? = null,
    val photos: List<Photo>,
    @SerialName("place_id")
    val placeId: String,
    @SerialName("plus_code")
    val plusCode: PlusCode,
    @SerialName("price_level")
    val priceLevel: Long? = null,
    val rating: Double,
    val reference: String,
    val scope: String,
    val types: List<String>,
    @SerialName("user_ratings_total")
    val userRatingsTotal: Long,
    val vicinity: String,
)

@Serializable
data class Geometry(
    val location: Location,
    val viewport: Viewport,
)

@Serializable
data class Location(
    val lat: Double,
    val lng: Double,
)

@Serializable
data class Viewport(
    val northeast: Northeast,
    val southwest: Southwest,
)

@Serializable
data class Northeast(
    val lat: Double,
    val lng: Double,
)

@Serializable
data class Southwest(
    val lat: Double,
    val lng: Double,
)

@Serializable
data class OpeningHours(
    @SerialName("open_now")
    val openNow: Boolean,
)

@Serializable
data class Photo(
    val height: Long,
    @SerialName("html_attributions")
    val htmlAttributions: List<String>,
    @SerialName("photo_reference")
    val photoReference: String,
    val width: Long,
)

@Serializable
data class PlusCode(
    @SerialName("compound_code")
    val compoundCode: String,
    @SerialName("global_code")
    val globalCode: String,
)

@Serializable
data class Response(val results: List<Place>)
