package com.elnimijogames.disneymovies.ui.detailsscreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.elnimijogames.disneymovies.ui.theme.DisneyMoviesTheme
import timber.log.Timber

@Composable
fun MovieDetailsScreen() {
    Timber.d("I have been loaded")
    Text("Hi Mum")
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenPreview() {
    DisneyMoviesTheme() {
        MovieDetailsScreen()
    }
}