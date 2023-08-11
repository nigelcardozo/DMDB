package com.elnimijogames.disneymovies.model

import androidx.paging.PagingSource
import com.elnimijogames.disneymovies.AppDispatchers
import com.elnimijogames.disneymovies.MainCoroutineRule
import com.elnimijogames.disneymovies.model.api.TMDBWebService
import com.elnimijogames.disneymovies.model.responses.DiscoverMoviesResponse
import com.elnimijogames.disneymovies.model.responses.MovieData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesPagingSourceTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val tmdbWebService = mockk<TMDBWebService>()

    private lateinit var pagingSource: PagingSource<Int, MovieData>

    @Before
    fun setup() {
        pagingSource = MoviesPagingSource(tmdbWebService, AppDispatchers(IO = mainCoroutineRule.dispatcher))
    }

    @Test
    fun `Test paging source load`() = mainCoroutineRule.runBlockingTest {
        // Create mock data
        val mockData = createMockMovieDataList()

        // Mock API response and complete the coroutine
        coEvery { tmdbWebService.getDiscoverMovies(1) } returns DiscoverMoviesResponse(1, mockData, 1, 20)

        // Load data from the paging source
        val params = PagingSource.LoadParams.Refresh<Int>(
            key = 1,
            loadSize = 20,
            placeholdersEnabled = false
        )
        val result = pagingSource.load(params)

        // Verify the result
        when (result) {
            is PagingSource.LoadResult.Page -> {
                val resultList = result.data.toList()
                assertEquals(mockData, resultList)
            }
            is PagingSource.LoadResult.Error -> {
                //fail("Error loading data: ${result.throwable}")
                fail("Error loading data")
            }
            else -> {
                //fail("Unknown result type: $result")
                fail("Unknown result type")
            }
        }
    }

    //ToDo - Add Error Handling and then appropriate test cases

    private fun createMockMovieDataList(): List<MovieData> {
        // Create and return a list of mock movie data
        // Replace this with your actual mock data creation logic
        return arrayListOf(
            createDummyMovieData(id = 1),
            createDummyMovieData(id = 2),
            createDummyMovieData(id = 3)
        )
    }

    private fun createDummyMovieData(
        id: Int = 1,
        title: String = "Dummy Movie",
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
