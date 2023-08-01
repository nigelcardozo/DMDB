package com.elnimijogames.disneymovies.model

data class MovieDetails (
    val id: Int,
    val title: String,
    val backdropPath: String,
    val posterPath: String,
    val originalLanguage: String,
    val releaseDate: String
    //val voteAverage: Float
    )