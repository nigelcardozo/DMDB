package com.elnimijogames.disneymovies.model

import com.elnimijogames.disneymovies.model.api.TMDBWebService
import com.elnimijogames.disneymovies.model.api.TMDBWebServiceImpl
import com.elnimijogames.disneymovies.model.responses.MovieDetailsResponse
import javax.inject.Inject

class MovieRepository
@Inject constructor(
    private val tmdbWebService: TMDBWebService
) {
    suspend fun getMovie(id: Int): MovieDetailsResponse {
        return tmdbWebService.getMovieDetails(id)
    }
}