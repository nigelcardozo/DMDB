package com.elnimijogames.disneymovies.model

import com.elnimijogames.disneymovies.model.api.TMDBWebService
import com.elnimijogames.disneymovies.model.responses.DiscoverMoviesResponse

class MoviesListRepository(private val tmdbWebService: TMDBWebService = TMDBWebService()) {
    private var cachedDiscoverMoviesResponse:DiscoverMoviesResponse = DiscoverMoviesResponse(
        0,
        arrayListOf(),
        0,
        0
    )

    suspend fun getDiscoverMovies(page: Int): DiscoverMoviesResponse {
        val response = tmdbWebService.getDiscoverMovies(page)
        cachedDiscoverMoviesResponse = response
        return response
    }

//    fun getMovie(movieId: String): DiscoverMoviesResponse? {
//        return cachedDiscoverMoviesResponse.firstOrNull() {
//            it.id == movieId
//        }
//    }
}