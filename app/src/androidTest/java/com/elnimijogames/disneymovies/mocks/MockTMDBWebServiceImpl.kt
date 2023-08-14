package com.elnimijogames.disneymovies.mocks

import com.elnimijogames.disneymovies.dummydata.DummyMovieData
import com.elnimijogames.disneymovies.model.api.TMDBWebService
import com.elnimijogames.disneymovies.model.responses.DiscoverMoviesResponse
import com.elnimijogames.disneymovies.model.responses.MovieDetailsResponse

class MockTMDBWebServiceImpl: TMDBWebService {
    private val dummyMovieData = DummyMovieData()

    override suspend fun getDiscoverMovies(page: Int): DiscoverMoviesResponse {
        return dummyMovieData.getDummyDiscoverMoviesResponse()
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse {
        TODO("Not yet implemented")
    }


}