package com.elnimijogames.disneymovies

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.elnimijogames.disneymovies.ui.splashscreen.SplashScreen
import com.elnimijogames.disneymovies.ui.theme.DisneyMoviesTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickStartButton_navigatesToNextScreen() {
        // Launch the Composable function with the navigation callback
        composeTestRule.setContent {
            SplashScreen(navigationCallback = {})
        }

        // Assert that the SplashScreen is displayed
        composeTestRule.onNodeWithText("Go").assertIsDisplayed()

    }

    @Test
    fun testSplashScreen_startButtonClicked_navigationCallbackInvoked() {
        var navigationCallbackInvoked = false

        // Launch the Composable function with the navigation callback
        composeTestRule.setContent {
            DisneyMoviesTheme {
                SplashScreen(navigationCallback = {
                    navigationCallbackInvoked = true
                })
            }
        }

        composeTestRule.onNodeWithText("Go").performClick()

        // Assert that the navigation callback was invoked
        assertTrue(navigationCallbackInvoked)
    }
}