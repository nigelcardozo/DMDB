package com.elnimijogames.disneymovies.model.responses

import com.google.gson.annotations.SerializedName

data class DiscoverMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieData>,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("total_results") val total_results: Int,
)
