package com.elnimijogames.disneymovies.movielist

import android.content.Context
import android.content.res.Resources
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.elnimijogames.disneymovies.model.StringResourceProvider
import com.elnimijogames.disneymovies.model.StringResourceProviderImpl
import com.elnimijogames.disneymovies.ui.movielist.MovieListScreen
import com.elnimijogames.disneymovies.ui.movielist.MovieListViewModel
import com.elnimijogames.disneymovies.ui.splashscreen.SplashScreen
import com.elnimijogames.disneymovies.ui.theme.DisneyMoviesTheme
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@CustomTestApplication(HiltTestApplication::class)
class MovieListScreenTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    @BindValue
    val mockMoviesListViewModel = mockk<MovieListViewModel>()

    @Before
    fun setup() {
        hiltTestRule.inject()
//        composeTestRule.setContent {
//            TallyApp(composeTestRule.activity.viewModels<ListViewModel>().value)
//        }
    }

    @Test
    fun movieItemClicked_navigatesToMovieDetailsScreen() {
        // Define a flag to track if the navigation callback was invoked
        var navigationCallbackInvoked = false

        // Launch the Composable function with the navigation callback
        composeTestRule.setContent {
            DisneyMoviesTheme() {
                MovieListScreen { movieId ->
                    // Set the flag to true when the navigation callback is invoked
                    navigationCallbackInvoked = true
                }
            }
        }

        // Perform a click on a movie item
        composeTestRule.onNodeWithContentDescription("Menu Thumbnail").performClick()

        // Assert that the navigation callback was invoked
        assertTrue(navigationCallbackInvoked)
    }

    // Add more UI tests as needed
}