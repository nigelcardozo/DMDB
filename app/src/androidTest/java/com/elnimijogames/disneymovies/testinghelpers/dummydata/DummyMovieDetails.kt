package com.elnimijogames.disneymovies.testinghelpers.dummydata

import com.elnimijogames.disneymovies.model.responses.DiscoverMoviesResponse
import com.elnimijogames.disneymovies.model.responses.Genres
import com.elnimijogames.disneymovies.model.responses.MovieData
import com.elnimijogames.disneymovies.model.responses.MovieDetailsResponse
import com.elnimijogames.disneymovies.model.responses.ProductionCompanies

class DummyMovieDetails {
    fun getDummyMovieDetailsResponse(): MovieDetailsResponse {
        return MovieDetailsResponse(
            id = 568124,
            budget = 50000,
            title = "Encanto",
            overview = "The tale of an extraordinary family, the Madrigals, who live hidden in the mountains of Colombia, in a magical house, in a vibrant town, in a wondrous, charmed place called an Encanto. The magic of the Encanto has blessed every child in the family—every child except one, Mirabel. But when she discovers that the magic surrounding the Encanto is in danger, Mirabel decides that she, the only ordinary Madrigal, might just be her exceptional family's last hope.",
            releaseDate = "2021-10-13",
            revenue = 253000000,
            runtime = 102,
            status = "released",
            tagline = "There's a little magic in all of us...almost all of us.",
            voteAverage = 7.637,
            genres = getDummyGenreData(),
            productionCompanies = getDummyProductionCompanyData()
        )
    }

    private fun getDummyGenreData(): ArrayList<Genres> {
        return arrayListOf(
            Genres(16, "Animation"),
            Genres(35, "Comedy"),
            Genres(10751, "Family"),
            Genres(14, "Fantasy"),
        )
    }

    private fun getDummyProductionCompanyData(): ArrayList<ProductionCompanies> {
        return arrayListOf(
            ProductionCompanies(
                6125,
                "/tzsMJBJZINu7GHzrpYzpReWhh66.png",
                "Walt Disney Animation Studios",
                "US"
            ),
            ProductionCompanies(
                2,
                "/wdrCwmRnLFJhEoH8GSfymY85KHT.png",
                "Walt Disney Pictures",
                "US"
            )
        )
    }
}