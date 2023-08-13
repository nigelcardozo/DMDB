package com.elnimijogames.disneymovies.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elnimijogames.disneymovies.AppDispatchers
import com.elnimijogames.disneymovies.model.api.TMDBWebService
import com.elnimijogames.disneymovies.model.api.TMDBWebServiceImpl
import com.elnimijogames.disneymovies.model.responses.MovieData
import kotlinx.coroutines.withContext

class MoviesPagingSource(
    private val tmdbWebService: TMDBWebService,
    private val appDispatchers: AppDispatchers
): PagingSource<Int, MovieData>() {
    override fun getRefreshKey(state: PagingState<Int, MovieData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieData> {
        return try {
            val page = params.key ?: 1
            val response = withContext(appDispatchers.IO) {
                tmdbWebService.getDiscoverMovies(page = page)
            }

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}