package com.elnimijogames.disneymovies.model.api

import com.elnimijogames.disneymovies.model.responses.DiscoverMoviesResponse
import com.elnimijogames.disneymovies.model.responses.MovieDetailsResponse

interface TMDBWebService {

    suspend fun getDiscoverMovies(page: Int): DiscoverMoviesResponse
    suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse
}