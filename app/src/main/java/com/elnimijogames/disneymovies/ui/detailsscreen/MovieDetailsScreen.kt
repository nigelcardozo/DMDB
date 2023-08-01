package com.elnimijogames.disneymovies.ui.detailsscreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.elnimijogames.disneymovies.model.MovieDetails
import com.elnimijogames.disneymovies.ui.theme.DisneyMoviesTheme
import timber.log.Timber

@Composable
fun MovieDetailsScreen(movieDetails: MovieDetails?) {
    Timber.d("MovieDetails == " + movieDetails?.id)
    Text("movie == " + movieDetails?.title + " date == " + movieDetails?.releaseDate)
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenPreview() {
    DisneyMoviesTheme() {
        //MovieDetailsScreen()
    }
}