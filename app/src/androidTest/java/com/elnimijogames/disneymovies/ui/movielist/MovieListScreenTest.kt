package com.elnimijogames.disneymovies.ui.movielist

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
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

    var navigationCallbackInvoked = false

    @Before
    fun setup() {
        hiltRule.inject()

        navigationCallbackInvoked = false

        composeRule.activity.setContent {
            val navController = rememberNavController()

            DisneyMoviesTheme {
                NavHost(navController, startDestination = "movie_list_screen") {
                    composable(route = "movie_list_screen") {
                        MovieListScreen(movieDetailsNavigationCallback = { navigationCallbackInvoked = true })
                    }
                }
            }
        }
    }

    @Test
    fun testMovieListScreen_checkMenuItemTextIsDisplayed() {
        composeRule.onNodeWithText("Dummy Movie 1").assertIsDisplayed()
        composeRule.onNodeWithText("Dummy Movie 2").assertIsDisplayed()
        composeRule.onNodeWithText("Dummy Movie 3").assertIsDisplayed()
        composeRule.onNodeWithText("Dummy Movie 4").assertIsDisplayed()

        composeRule.onNodeWithText("Dummy Movie 5").assertIsNotDisplayed()
        composeRule.onNodeWithText("Dummy Movie 6").assertIsNotDisplayed()
    }

    @Test
    fun testMovieListScreen_scrollToNextSet_CheckItemIsDisplayed() {
        composeRule.onNodeWithText("Dummy Movie 1").assertIsDisplayed()
        composeRule.onNodeWithText("Dummy Movie 2").assertIsDisplayed()
        composeRule.onNodeWithText("Dummy Movie 3").assertIsDisplayed()
        composeRule.onNodeWithText("Dummy Movie 4").assertIsDisplayed()
        composeRule.onNodeWithText("Dummy Movie 5").assertIsNotDisplayed()
        composeRule.onNodeWithText("Dummy Movie 6").assertIsNotDisplayed()

        composeRule.onNodeWithText("Dummy Movie 5").performScrollTo()
        composeRule.waitForIdle()

        composeRule.onNodeWithText("Dummy Movie 5").assertIsDisplayed()
        composeRule.onNodeWithText("Dummy Movie 6").assertIsDisplayed()
    }

    @Test
    fun testMovieListScreen_clickImage_CheckCallbackIsCalled() {
        assertFalse(navigationCallbackInvoked)
        composeRule.onNodeWithTag("/dummy_poster_path1.jpg")
            .assertIsDisplayed()
            .performClick()
        assertTrue(navigationCallbackInvoked)
    }
}