package com.elnimijogames.disneymovies.ui.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.elnimijogames.disneymovies.model.MoviesListRepository
import com.elnimijogames.disneymovies.model.responses.MovieData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: MoviesListRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
        }
    }

    fun getMovieListPage(): Flow<PagingData<MovieData>> = repository.getDiscoverMoviesPage().cachedIn(viewModelScope)
}