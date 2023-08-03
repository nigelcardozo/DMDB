package com.elnimijogames.disneymovies.ui.detailsscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.elnimijogames.disneymovies.model.responses.Genres
import com.elnimijogames.disneymovies.model.responses.MovieDetailsResponse
import com.elnimijogames.disneymovies.model.responses.ProductionCompanies
import com.elnimijogames.disneymovies.ui.detailsscreen.MovieDetailConstants.AVERAGE_MOVIE
import com.elnimijogames.disneymovies.ui.detailsscreen.MovieDetailConstants.GOOD_MOVIE
import com.elnimijogames.disneymovies.ui.theme.DisneyMoviesTheme
import java.text.DecimalFormat
import kotlin.math.roundToInt

private val BASE_URL = "https://image.tmdb.org/t/p/w370_and_h556_multi_faces/"

@Composable
fun MovieDetailsScreen(movieDetailsResponse: MovieDetailsResponse, textColor: Color = Color.White) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = BASE_URL + movieDetailsResponse.backdropPath,
            contentDescription = "Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(top = 0.dp)
                .fillMaxHeight()
        )
        Card(
            Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black.copy(alpha = 0.6f))
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 12.dp),
                    text = movieDetailsResponse.title ?: "",
                    style = MaterialTheme.typography.displayMedium,
                    color = textColor
                )

                Text(
                    modifier = Modifier
                        .padding(top = 10.dp, end = 12.dp),
                    text = movieDetailsResponse.voteAverage?.toInt().toString() ?: "",
                    style = MaterialTheme.typography.displayMedium,
                    color = getTextColorForAverageScore(movieDetailsResponse.voteAverage?.toInt() ?: 0)
                )
            }

            if (movieDetailsResponse.productionCompanies.size > 0) {
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp, top = 0.dp),
                    text = movieDetailsResponse.productionCompanies.get(0)?.name ?: "",
                    style = MaterialTheme.typography.displaySmall,
                    color = textColor
                )
            }

            Text(
                modifier = Modifier
                    .padding(start = 10.dp, top = 2.dp, bottom = 10.dp),
                text =
                movieDetailsResponse.releaseDate.toString() +
                "   " +
                "(" +
                movieDetailsResponse.runtime.toString() + "min" +
                ")",
                style = MaterialTheme.typography.displaySmall,
                color = textColor
            )
        }
    }
}

private fun getTextColorForAverageScore(score: Int): Color {
    return when {
        score < AVERAGE_MOVIE -> Color.Red
        score < GOOD_MOVIE -> Color.Yellow
        else -> Color.Green
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenPreview() {
    DisneyMoviesTheme() {
        val movieDetailsResponse =
        MovieDetailsScreen(
            getDummyMovieDetailData(),
            Color.Blue
        )
    }
}

private fun getDummyMovieDetailData(): MovieDetailsResponse {
    return MovieDetailsResponse(
        id = 568124,
        budget = 50000,
        title = "Encanto",
        overview = "The tale of an extraordinary family, the Madrigals, who live hidden in the mountains of Colombia, in a magical house, in a vibrant town, in a wondrous, charmed place called an Encanto. The magic of the Encanto has blessed every child in the family—every child except one, Mirabel. But when she discovers that the magic surrounding the Encanto is in danger, Mirabel decides that she, the only ordinary Madrigal, might just be her exceptional family's last hope.",
        releaseDate = "2021-10-13",
        revenue = 253000000,
        runtime = 102,
        status = "released",
        tagline = "There's a little magic in all of us...almost all of us.",
        voteAverage = 7.637,
        genres = getDummyGenreData(),
        productionCompanies = getDummyProductionCompanyData()

    )
}

private fun getDummyGenreData(): ArrayList<Genres> {
    return arrayListOf(
        Genres(16, "Animation"),
        Genres(35, "Comedy"),
        Genres(10751, "Family"),
        Genres(14, "Fantasy"),
    )
}

private fun getDummyProductionCompanyData(): ArrayList<ProductionCompanies> {
    return arrayListOf(
        ProductionCompanies(
            6125,
            "/tzsMJBJZINu7GHzrpYzpReWhh66.png",
            "Walt Disney Animation Studios",
            "US"
        ),
        ProductionCompanies(
            2,
            "/wdrCwmRnLFJhEoH8GSfymY85KHT.png",
            "Walt Disney Pictures",
            "US"
        )
    )
}