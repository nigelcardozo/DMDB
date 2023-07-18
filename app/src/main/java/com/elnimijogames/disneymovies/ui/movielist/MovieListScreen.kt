package com.elnimijogames.disneymovies.ui.movielist

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
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.elnimijogames.disneymovies.model.MenuItem
import com.elnimijogames.disneymovies.ui.theme.DisneyMoviesTheme
import com.elnimijogames.disneymovies.ui.theme.SplashGradientEnd
import com.elnimijogames.disneymovies.ui.theme.SplashGradientStart
import timber.log.Timber


@Composable
//fun MovieListScreen(movieDetailsNavigationCallback: () -> Unit) {
fun MovieListScreen() {
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
        //TODO: Remove this, it's temporary for rendering tests.
        val myDummyList = arrayListOf<MenuItem>(
            MenuItem("https://image.tmdb.org/t/p/w370_and_h556_multi_faces/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg", "Encanto", "3"),
            MenuItem("https://image.tmdb.org/t/p/w370_and_h556_multi_faces/naya0zF4kT401Sx15AtwB9vpcJr.jpg", "Meet the Robinsons", "3"),
            MenuItem("https://image.tmdb.org/t/p/w370_and_h556_multi_faces/4x9FmvdJ464Fg7A9XcbYSmxfVw3.jpg", "Dumbo", "3"),
            MenuItem("https://image.tmdb.org/t/p/w370_and_h556_multi_faces/ym7Kst6a4uodryxqbGOxmewF235.jpg", "Tangled", "3"),
            MenuItem("https://image.tmdb.org/t/p/w370_and_h556_multi_faces/avz6S9HYWs4O8Oe4PenBFNX4uDi.jpg", "Cinderella", "3"),
            MenuItem("https://image.tmdb.org/t/p/w370_and_h556_multi_faces/2mxS4wUimwlLmI1xp6QW6NSU361.jpg", "Big Hero 6", "3"),
            MenuItem("https://image.tmdb.org/t/p/w370_and_h556_multi_faces/yprv5PbnEksoVj2v6XEnDBg9joR.jpg", "The Princess & the Frog", "3"),
            MenuItem("https://image.tmdb.org/t/p/w370_and_h556_multi_faces/kgwjIb2JDHRhNk13lmSxiClFjVk.jpg", "Frozen", "3"),
            MenuItem("https://image.tmdb.org/t/p/w370_and_h556_multi_faces/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg", "Raya", "3"),
            MenuItem("https://image.tmdb.org/t/p/w370_and_h556_multi_faces/v17k3Pwv1OEC4chJK49NOHqkbf8.jpg", "Snow White & the Seven Dwarfs", "3")
        )
        VerticalGridButtons(menuItems = myDummyList)
    }
}

@Composable
fun VerticalGridButtons(
    menuItems: ArrayList<MenuItem>
    //navigationCallback: (String) -> Unit,
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
//        item(
//            span = {
//                GridItemSpan(maxLineSpan)
//            },
//        ) {
//            HorizontalImageGallery(assetPaths, navigationGalleryCallback)
//        }
        items(menuItems) { menuItem ->
            MenuItemTile(menuItem)
        }
    }
}

@Composable
//fun MenuItemTile(navigationCallback:(String) -> Unit) {
fun MenuItemTile(menuItem: MenuItem) {
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
                model = menuItem.imagePath,
                contentDescription = "Menu Thumbnail",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(250.dp)
                    .padding(top = 0.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable(onClick = {
                        //Timber.d("menuItem clicked == " + menuItem.menuId)
                        //navigationCallback(menuItem.menuId)
                    }
                    )
            )

            Spacer(modifier = Modifier.height(height = 15.dp))

            val movieTitleScroll = rememberScrollState(0)
            Text(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)
                .horizontalScroll(movieTitleScroll),
                text = menuItem.menuName,
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
        //MovieListScreen({})
        MovieListScreen()
    }
}