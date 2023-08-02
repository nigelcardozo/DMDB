package com.elnimijogames.disneymovies.ui.movielist

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.elnimijogames.disneymovies.model.MovieDetails
import com.elnimijogames.disneymovies.model.responses.MovieData
import com.elnimijogames.disneymovies.ui.theme.DisneyMoviesTheme
import com.elnimijogames.disneymovies.ui.theme.SplashGradientEnd
import com.elnimijogames.disneymovies.ui.theme.SplashGradientStart
import timber.log.Timber

private val BASE_URL = "https://image.tmdb.org/t/p/w370_and_h556_multi_faces/"

@Composable
fun MovieListScreen(movieDetailsNavigationCallback: (Int) -> Unit) {
    val viewModel: MovieListViewModel = hiltViewModel()

    val movieDataList = viewModel.getMovieListPage().collectAsLazyPagingItems()

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
        VerticalGridButtons(movieDataList = movieDataList, movieDetailsNavigationCallback)
    }
}

@Composable
fun VerticalGridButtons(
    movieDataList: LazyPagingItems<MovieData>,
    navigationCallback: (Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = 8.dp,
            end = 8.dp,
            bottom = 8.dp,
            top = 100.dp
        ),
    ) {
        items(
            movieDataList.itemCount
        ) { index ->
            movieDataList[index]?.let { MenuItemTile(it, navigationCallback) }
        }
    }

    // TODO: Add error handling
}

@Composable
fun MenuItemTile(movieData: MovieData, navigationCallback: (Int) -> Unit) {
    Card(
        Modifier
            .padding(8.dp)
            .background(Color.Black.copy(alpha = 0.4f)),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(4.dp)
                .background(color = Color.Black.copy(alpha = 1.0f))
                .fillMaxWidth(),
        ) {
            AsyncImage(
                model = BASE_URL + movieData.posterPath,
                contentDescription = "Menu Thumbnail",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(250.dp)
                    .padding(top = 0.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable(onClick = {
                        Timber.d("onClick event for movie ID == " + movieData.id)
                        navigationCallback(movieData.id)
                    }
                )
            )

            Spacer(modifier = Modifier.height(height = 15.dp))

            val movieTitleScroll = rememberScrollState(0)
            Text(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)
                .horizontalScroll(movieTitleScroll),
                text = movieData.originalTitle,
                style = MaterialTheme.typography.displaySmall,
                color = Color.White,
                maxLines = 1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListScreenPreview() {
    DisneyMoviesTheme() {
        MovieListScreen({})
    }
}