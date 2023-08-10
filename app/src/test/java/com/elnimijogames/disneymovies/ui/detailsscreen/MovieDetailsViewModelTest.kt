package com.elnimijogames.disneymovies.ui.detailsscreen

import androidx.lifecycle.SavedStateHandle
import com.elnimijogames.disneymovies.AppDispatchers
import com.elnimijogames.disneymovies.MainCoroutineRule
import com.elnimijogames.disneymovies.model.MovieRepository
import com.elnimijogames.disneymovies.model.responses.Genres
import com.elnimijogames.disneymovies.model.responses.MovieDetailsResponse
import com.elnimijogames.disneymovies.model.responses.ProductionCompanies
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val movieRepository = mockk<MovieRepository> (relaxed = true)
    private val savedStateHandle = mockk<SavedStateHandle> (relaxed = true)

    private lateinit var viewModel: MovieDetailsViewModel

    private val testDispatcher = AppDispatchers(
        IO = TestCoroutineDispatcher()
    )

    @Before
    fun setup() {
        every { savedStateHandle.get<Int>("id") } returns 5
        viewModel = MovieDetailsViewModel(movieRepository, savedStateHandle, testDispatcher)
    }

    @Test
    fun `Loading state works`() = runBlockingTest {
        every { savedStateHandle["id"] = 5 }
        coEvery { movieRepository.getMovie(5) } returns getDummyMovieDetailData(5)

        viewModel = MovieDetailsViewModel(movieRepository, savedStateHandle, testDispatcher)
        Assert.assertEquals(getDummyMovieDetailData(5), viewModel.movieDetailsState.value)
    }

    private fun getDummyMovieDetailData(id: Int): MovieDetailsResponse {
        return MovieDetailsResponse(
            id = id,
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