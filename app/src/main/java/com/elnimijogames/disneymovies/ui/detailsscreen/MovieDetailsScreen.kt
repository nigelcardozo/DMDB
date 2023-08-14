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
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.elnimijogames.disneymovies.model.responses.Genres
import com.elnimijogames.disneymovies.model.responses.MovieDetailsResponse
import com.elnimijogames.disneymovies.model.responses.ProductionCompanies
import com.elnimijogames.disneymovies.ui.MovieConstants.AVERAGE_MOVIE
import com.elnimijogames.disneymovies.ui.MovieConstants.BASE_URL
import com.elnimijogames.disneymovies.ui.MovieConstants.GOOD_MOVIE
import com.elnimijogames.disneymovies.ui.MovieConstants.MAX_LINES_OVERVIEW
import com.elnimijogames.disneymovies.ui.MovieConstants.MIN_LINES_OVERVIEW
import com.elnimijogames.disneymovies.ui.theme.DisneyMoviesTheme

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
                            .padding(top = 10.dp, start = 12.dp)
                            .semantics {
                               testTag = "md_headline_card_movie_title"
                            },
                        text = movieDetailsResponse.title ?: "",
                        style = MaterialTheme.typography.displayMedium,
                        color = textColor
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 10.dp, end = 12.dp)
                            .semantics {
                                testTag = "md_headline_card_vote_average"
                            },
                        text = movieDetailsResponse.voteAverage?.toInt().toString(),
                        style = MaterialTheme.typography.displayMedium,
                        color = getTextColorForAverageScore(movieDetailsResponse.voteAverage?.toInt() ?: 0)
                    )
                }

                if (movieDetailsResponse.productionCompanies.size > 0) {
                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp, top = 0.dp)
                            .semantics {
                                testTag = "md_headline_card_production_companies"
                            },
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
                            .padding(start = 10.dp, top = 0.dp, bottom = bottomPadding)
                            .semantics {
                                testTag = "md_headline_card_release_date"
                            },
                        text =
                        movieDetailsResponse.releaseDate.toString() +
                                "   " +
                                "(" +
                                movieDetailsResponse.runtime.toString() + " min" +
                                ")",
                        style = MaterialTheme.typography.displaySmall,
                        color = textColor
                    )

                    if (!isHeadlineExpanded) {
                        Icon(
                            modifier = Modifier
                                .padding(top = 0.dp, end = 12.dp, bottom = bottomPadding)
                                .semantics {
                                    testTag = "md_headline_card_icon_expand_more"
                                }
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
                            .padding(start = 10.dp, top = 0.dp)
                            .semantics {
                                testTag = "md_headline_card_budget"
                            },
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
                                .padding(start = 10.dp, top = 0.dp, bottom = 10.dp)
                                .semantics {
                                    testTag = "md_headline_card_revenue"
                                },
                            text = "Revenue: " + movieDetailsResponse.revenue.toString(),
                            style = MaterialTheme.typography.displaySmall,
                            color = textColor
                        )

                        if (isHeadlineExpanded) {
                            Icon(
                                modifier = Modifier
                                    .padding(top = 0.dp, end = 12.dp, bottom = 2.dp)
                                    .clickable { isHeadlineExpanded = !isHeadlineExpanded }
                                    .semantics {
                                        testTag = "md_headline_card_icon_expand_less"
                                    },
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = "Expand Less",
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
                            .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                            .semantics {
                                testTag = "md_overview_card_overview"
                            },
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
                            .horizontalScroll(textGenresScrollState)
                            .semantics {
                                testTag = "md_tags_card"
                            },
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

private fun getMovieGenresString(movieGenres: ArrayList<Genres>): String {
    return movieGenres.joinToString(", ") { it.name.toString() }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenPreview() {
    DisneyMoviesTheme() {
        MovieDetailsScreen(
            DummyMovieDetails().getDummyMovieDetailsResponse(),
            Color.Blue
        )
    }
}