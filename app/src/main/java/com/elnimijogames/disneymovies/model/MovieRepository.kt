package com.elnimijogames.disneymovies.model

import com.elnimijogames.disneymovies.model.api.TMDBWebService
import com.elnimijogames.disneymovies.model.responses.MovieDetailsResponse

class MovieRepository(private val tmdbWebService: TMDBWebService = TMDBWebService()) {
    suspend fun getMovie(id: Int): MovieDetailsResponse {
        return tmdbWebService.getMovieDetails(id)
    }
}