package com.elnimijogames.disneymovies.model.responses

import com.google.gson.annotations.SerializedName

data class TMDBDiscoverMoviesResponse(val discoverMoviesResponse: DiscoverMoviesResponse)

data class DiscoverMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: ArrayList<MovieData>?,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("total_results") val total_results: Int,
)

data class MovieData(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("genre_ids") val genreIds: ArrayList<Int>,
    @SerializedName("id") val id: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overView: String,
    @SerializedName("popularity") val popularity: Float,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("vote_count") val voteCount: Int
)
