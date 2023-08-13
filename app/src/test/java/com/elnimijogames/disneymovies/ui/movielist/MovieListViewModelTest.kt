package com.elnimijogames.disneymovies.ui.movielist

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.elnimijogames.disneymovies.AppDispatchers
import com.elnimijogames.disneymovies.MainCoroutineRule
import com.elnimijogames.disneymovies.model.MoviesPagingSource
import com.elnimijogames.disneymovies.model.api.TMDBWebServiceImpl
import com.elnimijogames.disneymovies.model.responses.DiscoverMoviesResponse
import com.elnimijogames.disneymovies.model.responses.MovieData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieListViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

//    private val tmdbWebService = mockk<TMDBWebService>(relaxed = true)
    private val moviesPagingSource = mockk<MoviesPagingSource>(relaxed = true)
    private val tmdbWebService = mockk<TMDBWebServiceImpl>()
//    private val moviesPagingSource = mockk<MoviesPagingSource>()

    private lateinit var viewModel: MovieListViewModel

    @Before
    fun setup() {
        viewModel = MovieListViewModel(AppDispatchers(IO = mainCoroutineRule.dispatcher), moviesPagingSource)
    }

    @Test
    fun `Test fetching movie list`() = mainCoroutineRule.runBlockingTest {
        // Create mock movie data
        val mockMovieData = createDummyMovieDataResponse()

        // Mock API response
        coEvery { tmdbWebService.getDiscoverMovies(any()) } returns createDummyMovieDataResponse()
        coEvery { moviesPagingSource.load(any()) } returns PagingSource.LoadResult.Page(createMockMovieDataList(), null, null)
        //coEvery { tmdbWebService.getDiscoverMovies(1) } returns createDummyMovieDataResponse()

        //val movieDataList = viewModel.items.collectAsLazyPagingItems()
        val movieDataList: Flow<PagingData<MovieData>> = viewModel.items.take(1)

        // Collect the paging data flow and verify the items
        viewModel.items.collect { pagingData ->
            Assert.assertEquals(mockMovieData, viewModel.items)
        }
        //val collectedItems = mutableListOf<MovieData>()
        //Flow<PagingData<MovieData>>

        // Collect the paging data flow and add items to the list
//        viewModel.items.collect { pagingData ->
//            //val collectedItems = pagingData.toList()
//            //Assert.assertEquals(mockMovieData, collectedItems)
//        }
    }

    fun createDummyMovieDataResponse(): DiscoverMoviesResponse {
        return DiscoverMoviesResponse(
            page = 1,
            total_pages = 5,
            total_results = 40,
            results = createMockMovieDataList()
        )
    }

    private fun createMockMovieDataList(): List<MovieData> {
        // Create and return a list of mock movie data
        // Replace this with your actual mock data creation logic
        return listOf(
            createDummyMovieData(id = 1),
            createDummyMovieData(id = 2),
            createDummyMovieData(id = 3)
        )
    }

    fun createDummyMovieData(
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