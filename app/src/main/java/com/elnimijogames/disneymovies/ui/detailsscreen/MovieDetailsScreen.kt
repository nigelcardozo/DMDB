package com.elnimijogames.disneymovies.ui.detailsscreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.elnimijogames.disneymovies.model.MovieDetails
import com.elnimijogames.disneymovies.ui.theme.DisneyMoviesTheme
import timber.log.Timber

@Composable
fun MovieDetailsScreen(movieDetails: MovieDetails?) {
    Timber.d("MovieDetails == " + movieDetails?.id)
    Text(" movie == " + movieDetails?.title +
            " id == " + movieDetails?.id +
            " date == " + movieDetails?.releaseDate +
            " language == " + movieDetails?.originalLanguage +
            " voteAverage == " + movieDetails?.voteAverage +
            " voteCount == " + movieDetails?.voteCount +
            " backdropPath == " + movieDetails?.backdropPath +
            " posterPath == " + movieDetails?.posterPath
    )
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenPreview() {
    DisneyMoviesTheme() {
        //MovieDetailsScreen()
    }
}