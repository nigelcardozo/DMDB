package com.elnimijogames.disneymovies.testinghelpers.dummydata

import com.elnimijogames.disneymovies.model.responses.DiscoverMoviesResponse
import com.elnimijogames.disneymovies.model.responses.MovieData

class DummyMovieData {
    fun getDummyDiscoverMoviesResponse(): DiscoverMoviesResponse {
        return DiscoverMoviesResponse(
            page = 1,
            total_pages = 1,
            total_results = 10,
            results = getDummyMovieDataList()
        )
    }

    private fun getDummyMovieDataList(): List<MovieData> {
        // Create and return a list of mock movie data
        // Replace this with your actual mock data creation logic
        return listOf(
            getDummyMovieData(id = 1),
            getDummyMovieData(id = 2),
            getDummyMovieData(id = 3),
            getDummyMovieData(id = 4),
            getDummyMovieData(id = 5),
            getDummyMovieData(id = 6),
            getDummyMovieData(id = 7),
            getDummyMovieData(id = 8),
            getDummyMovieData(id = 9),
            getDummyMovieData(id = 10)
        )
    }

    private fun getDummyMovieData(
        id: Int = 1,
        title: String = "Dummy Movie $id",
        overview: String = "This is a dummy movie overview",
        releaseDate: String = "2023-08-01",
        voteAverage: Double = 7.5
    ): MovieData {
        return MovieData(
            adult = false,
            backdropPath = "/dummy_backdrop_path$id.jpg",
            genreIds = listOf(1, 2, 3),
            id = id,
            originalLanguage = "en",
            originalTitle = title,
            overView = overview,
            popularity = 123.45,
            posterPath = "/dummy_poster_path$id.jpg",
            releaseDate = releaseDate,
            title = title,
            video = false,
            voteAverage = voteAverage,
            voteCount = 100
        )
    }
}