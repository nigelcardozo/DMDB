package com.elnimijogames.disneymovies.ui.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.elnimijogames.disneymovies.model.MoviesListRepository
import com.elnimijogames.disneymovies.model.MoviesPagingSource
import com.elnimijogames.disneymovies.model.api.TMDBWebService
import com.elnimijogames.disneymovies.model.responses.MovieData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MoviesListRepository,
    private val tmdbWebService: TMDBWebService) : ViewModel() {

    val items = Pager(
    config = PagingConfig(
    pageSize = 20,
    ),
    pagingSourceFactory = {
        MoviesPagingSource(tmdbWebService)
    }
    ).flow.cachedIn(viewModelScope)

    init {
        viewModelScope.launch(Dispatchers.IO) {
        }
    }

    //fun getMovieListPage(): Flow<PagingData<MovieData>> = repository.getDiscoverMoviesPage().cachedIn(viewModelScope)
}