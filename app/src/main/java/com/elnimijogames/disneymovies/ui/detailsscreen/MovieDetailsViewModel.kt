package com.elnimijogames.disneymovies.ui.detailsscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.elnimijogames.disneymovies.model.MovieDetails

class MovieDetailsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    var movieDetailsState = mutableStateOf<MovieDetails?>(null)

    init {
        val id = savedStateHandle.get<Int>("id")?: 0
        val title = savedStateHandle.get<String>("title")?: ""
        val backdropPath = savedStateHandle.get<String>("backdropPath")?: ""
        val posterPath = savedStateHandle.get<String>("posterPath")?: ""
        val originalLanguage = savedStateHandle.get<String>("originalLanguage")?: ""
        val releaseDate = savedStateHandle.get<String>("releaseDate")?: ""
        //val voteAverage = savedStateHandle.get<Float>("voteAverage")?: 0.0

        movieDetailsState.value = MovieDetails(id, title, backdropPath, posterPath, originalLanguage, releaseDate)
    }
}