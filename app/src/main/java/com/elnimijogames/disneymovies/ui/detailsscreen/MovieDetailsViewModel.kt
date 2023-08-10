package com.elnimijogames.disneymovies.ui.detailsscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elnimijogames.disneymovies.AppDispatchers
import com.elnimijogames.disneymovies.model.MovieRepository
import com.elnimijogames.disneymovies.model.responses.MovieDetailsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val savedStateHandle: SavedStateHandle,
    private val appDispatchers: AppDispatchers,
    ): ViewModel() {

    val movieDetailsState: MutableState<MovieDetailsResponse> = mutableStateOf(MovieDetailsResponse())

    init {
        val movieId = savedStateHandle.get<Int>("id") ?: 568124
        viewModelScope.launch {
            val movieDetails = withContext(appDispatchers.IO) {
                getMovieDetails(movieId)
            }
            movieDetailsState.value = movieDetails
        }
    }

    private suspend fun getMovieDetails(id: Int): MovieDetailsResponse {
        return repository.getMovie(id)
    }
}