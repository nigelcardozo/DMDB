package com.elnimijogames.disneymovies.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.elnimijogames.disneymovies.model.api.TMDBWebService

class MoviesListRepository(private val tmdbWebService: TMDBWebService = TMDBWebService()) {
    fun getDiscoverMoviesPage() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            MoviesPagingSource(tmdbWebService)
        }
    ).flow
}