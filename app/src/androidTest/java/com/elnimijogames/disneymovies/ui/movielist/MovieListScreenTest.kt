package com.elnimijogames.disneymovies.ui.movielist

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elnimijogames.disneymovies.MainActivity
import com.elnimijogames.disneymovies.di.AppModule
import com.elnimijogames.disneymovies.ui.theme.DisneyMoviesTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class MovieListScreenTest {

    @get:Rule (order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule (order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()
    //val composeRule = createComposeRule()

    @Before
    fun setup() {
        hiltRule.inject()

        composeRule.activity.setContent {
            val navController = rememberNavController()

            DisneyMoviesTheme {
                NavHost(navController, startDestination = "movie_list_screen") {
                    composable(route = "movie_list_screen") {
                        MovieListScreen(movieDetailsNavigationCallback = { })
                    }
                }
            }
        }
    }

    @Test
    fun checkMenuItemsAreDisplayed() {
        composeRule.onNodeWithText("Dummy Movie 1").assertIsDisplayed()
    }
}