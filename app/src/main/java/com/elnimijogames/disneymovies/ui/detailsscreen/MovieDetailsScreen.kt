package com.elnimijogames.disneymovies.ui.detailsscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.elnimijogames.disneymovies.model.MovieDetails
import com.elnimijogames.disneymovies.ui.movielist.VerticalGridButtons
import com.elnimijogames.disneymovies.ui.movielist.getMovieDetails
import com.elnimijogames.disneymovies.ui.theme.DisneyMoviesTheme
import com.elnimijogames.disneymovies.ui.theme.SplashGradientEnd
import com.elnimijogames.disneymovies.ui.theme.SplashGradientStart
import timber.log.Timber

private val BASE_URL = "https://image.tmdb.org/t/p/w370_and_h556_multi_faces/"

@Composable
fun MovieDetailsScreen(movieDetails: MovieDetails?) {
    Timber.d("MovieDetails == " + movieDetails?.id)
//    Text(" movie == " + movieDetails?.title +
//            " id == " + movieDetails?.id +
//            " date == " + movieDetails?.releaseDate +
//            " language == " + movieDetails?.originalLanguage +
//            " voteAverage == " + movieDetails?.voteAverage +
//            " voteCount == " + movieDetails?.voteCount +
//            " backdropPath == " + movieDetails?.backdropPath +
//            " posterPath == " + movieDetails?.posterPath
//    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        SplashGradientStart,
                        SplashGradientEnd
                    )
                )
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        AsyncImage(
            model = BASE_URL + movieDetails?.backdropPath,
            contentDescription = "Background",
            //contentScale = ContentScale.FillBounds,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(top = 0.dp)
                .fillMaxHeight()
        )
        Card(Modifier
            .padding(8.dp)
            .size(400.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black.copy(alpha = 0.6f))
        ) {

            Text(
                text = movieDetails?.title ?: "",
                style = MaterialTheme.typography.displayMedium,
                color = Color.White
            )

            Text(
                text = movieDetails?.releaseDate ?: "",
                style = MaterialTheme.typography.displaySmall,
                color = Color.White
            )

            Text(
                text = movieDetails?.voteAverage ?: "",
                style = MaterialTheme.typography.displaySmall,
                color = Color.White
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenPreview() {
    DisneyMoviesTheme() {
        MovieDetailsScreen(MovieDetails(0, "", "", "", "", "", "", 0))
    }
}