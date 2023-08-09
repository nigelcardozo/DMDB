package com.elnimijogames.disneymovies.ui.detailsscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.elnimijogames.disneymovies.model.responses.Genres
import com.elnimijogames.disneymovies.model.responses.MovieDetailsResponse
import com.elnimijogames.disneymovies.model.responses.ProductionCompanies
import com.elnimijogames.disneymovies.ui.detailsscreen.MovieDetailConstants.AVERAGE_MOVIE
import com.elnimijogames.disneymovies.ui.detailsscreen.MovieDetailConstants.GOOD_MOVIE
import com.elnimijogames.disneymovies.ui.detailsscreen.MovieDetailConstants.MAX_LINES_OVERVIEW
import com.elnimijogames.disneymovies.ui.detailsscreen.MovieDetailConstants.MIN_LINES_OVERVIEW
import com.elnimijogames.disneymovies.ui.theme.DisneyMoviesTheme

private val BASE_URL = "https://image.tmdb.org/t/p/w370_and_h556_multi_faces/"

@Composable
fun MovieDetailsScreen(movieDetailsResponse: MovieDetailsResponse, textColor: Color = Color.White) {
    var isOverviewExpanded by remember { mutableStateOf(false) }
    var isHeadlineExpanded by remember { mutableStateOf(false) }

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

        Column(modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                        text = movieDetailsResponse.voteAverage?.toInt().toString(),
                        style = MaterialTheme.typography.displayMedium,
                        color = getTextColorForAverageScore(movieDetailsResponse.voteAverage?.toInt() ?: 0)
                    )
                }

                if (movieDetailsResponse.productionCompanies.size > 0) {
                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp, top = 0.dp),
                        text = movieDetailsResponse.productionCompanies.get(0).name ?: "",
                        style = MaterialTheme.typography.displaySmall,
                        color = textColor
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val bottomPadding: Dp = if (isHeadlineExpanded) 0.dp else 10.dp

                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp, top = 0.dp, bottom = bottomPadding),
                        text =
                        movieDetailsResponse.releaseDate.toString() +
                                "   " +
                                "(" +
                                movieDetailsResponse.runtime.toString() + "min" +
                                ")",
                        style = MaterialTheme.typography.displaySmall,
                        color = textColor
                    )

                    if (!isHeadlineExpanded) {
                        Icon(
                            modifier = Modifier
                                .padding(top = 0.dp, end = 12.dp, bottom = bottomPadding)
                                .clickable { isHeadlineExpanded = !isHeadlineExpanded },
                            //imageVector = R.drawable.expand_more,
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Expand More",
                        )
                    }
                }

                if (isHeadlineExpanded) {
                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp, top = 0.dp),
                        text = "Budget: " + movieDetailsResponse.budget.toString(),
                        style = MaterialTheme.typography.displaySmall,
                        color = textColor
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(start = 10.dp, top = 0.dp, bottom = 10.dp),
                            text = "Revenue: " + movieDetailsResponse.revenue.toString(),
                            style = MaterialTheme.typography.displaySmall,
                            color = textColor
                        )

                        if (isHeadlineExpanded) {
                            Icon(
                                modifier = Modifier
                                    .padding(top = 0.dp, end = 12.dp, bottom = 2.dp)
                                    .clickable { isHeadlineExpanded = !isHeadlineExpanded },
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = "Expand More",
                            )
                        }
                    }
                }
            }

            Column() {
                Card(
                    Modifier
                        .padding(start = 4.dp, end = 4.dp, bottom = 2.dp)
                        .fillMaxWidth()
                        .clickable { isOverviewExpanded = !isOverviewExpanded },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black.copy(alpha = 0.6f))
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = if (isOverviewExpanded) MAX_LINES_OVERVIEW else MIN_LINES_OVERVIEW,
                        style = MaterialTheme.typography.displaySmall,
                        color = textColor,
                        text = movieDetailsResponse.overview.toString()
                    )
                }

                Card(
                    Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 4.dp)
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black.copy(alpha = 0.7f)
                    ),
                    shape = RectangleShape
                ) {
                    val textGenresScrollState = rememberScrollState(0)
                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                            .horizontalScroll(textGenresScrollState),
                        text = getMovieGenresString(movieDetailsResponse.genres),
                        style = MaterialTheme.typography.displaySmall,
                        color = textColor,
                        maxLines = 1
                    )
                }
            }
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

//private fun getMovieGenresString(movieGenres: ArrayList<Genres>): String {
//    var response = ""
//
//    for ((index, movieGenre) in movieGenres.withIndex()) {
//        response += movieGenre.name
//
//        if (index != movieGenres.size -1) {
//            response += ", "
//        }
//    }
//
//    return response
//}

private fun getMovieGenresString(movieGenres: ArrayList<Genres>): String {
    return movieGenres.joinToString(", ") { it.name.toString() }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenPreview() {
    DisneyMoviesTheme() {
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