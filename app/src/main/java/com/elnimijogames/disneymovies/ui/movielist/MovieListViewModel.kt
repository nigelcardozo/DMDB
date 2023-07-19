package com.elnimijogames.disneymovies.ui.movielist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elnimijogames.disneymovies.model.MoviesListRepository
import com.elnimijogames.disneymovies.model.responses.DiscoverMoviesResponse
import com.elnimijogames.disneymovies.model.responses.MovieData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: MoviesListRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            var discoverMoviesResponse = getMovieList()
            movieDataState.value = discoverMoviesResponse?.results ?: arrayListOf()
        }
    }

    val movieDataState: MutableState<List<MovieData>> = mutableStateOf(emptyList())

    private suspend fun getMovieList(): DiscoverMoviesResponse? {
        return repository.getDiscoverMovies()
    }
}