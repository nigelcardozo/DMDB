package com.elnimijogames.disneymovies.mocks

import com.elnimijogames.disneymovies.model.api.TMDBWebService
import com.elnimijogames.disneymovies.model.responses.DiscoverMoviesResponse
import com.elnimijogames.disneymovies.model.responses.MovieData
import com.elnimijogames.disneymovies.model.responses.MovieDetailsResponse

class MockTMDBWebServiceImpl: TMDBWebService {
    override suspend fun getDiscoverMovies(page: Int): DiscoverMoviesResponse {
        return createDummyMovieDataResponse()
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse {
        TODO("Not yet implemented")
    }

    fun createDummyMovieDataResponse(): DiscoverMoviesResponse {
        return DiscoverMoviesResponse(
            page = 1,
            total_pages = 1,
            total_results = 10,
            results = createMockMovieDataList()
        )
    }

    private fun createMockMovieDataList(): List<MovieData> {
        // Create and return a list of mock movie data
        // Replace this with your actual mock data creation logic
        return listOf(
            createDummyMovieData(id = 1),
            createDummyMovieData(id = 2),
            createDummyMovieData(id = 3),
            createDummyMovieData(id = 4),
            createDummyMovieData(id = 5),
            createDummyMovieData(id = 6),
            createDummyMovieData(id = 7),
            createDummyMovieData(id = 8),
            createDummyMovieData(id = 9),
            createDummyMovieData(id = 10)
        )
    }

    fun createDummyMovieData(
        id: Int = 1,
        title: String = "Dummy Movie $id",
        overview: String = "This is a dummy movie overview",
        releaseDate: String = "2023-08-01",
        voteAverage: Double = 7.5
    ): MovieData {
        return MovieData(
            adult = false,
            backdropPath = "/dummy_backdrop_path.jpg",
            genreIds = listOf(1, 2, 3),
            id = id,
            originalLanguage = "en",
            originalTitle = title,
            overView = overview,
            popularity = 123.45,
            posterPath = "/dummy_poster_path.jpg",
            releaseDate = releaseDate,
            title = title,
            video = false,
            voteAverage = voteAverage,
            voteCount = 100
        )
    }
}