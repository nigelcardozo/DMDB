package com.elnimijogames.disneymovies

import android.content.Context
import android.content.res.Resources
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.elnimijogames.disneymovies.model.StringResourceProvider
import com.elnimijogames.disneymovies.model.StringResourceProviderImpl
import com.elnimijogames.disneymovies.ui.splashscreen.SplashScreen
import com.elnimijogames.disneymovies.ui.theme.DisneyMoviesTheme
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    val mockContext = mockk<Context>(relaxed = true)
    val mockResources = mockk<Resources>(relaxed = true)

    @RelaxedMockK
    private lateinit var stringResourceProvider: StringResourceProvider


    @Test
    fun clickStartButton_navigatesToNextScreen() {
        stringResourceProvider = StringResourceProviderImpl(mockResources)

        every { mockContext.getString(R.string.login_go, "")} returns "Go"
        every { stringResourceProvider.getString(R.string.login_go, "") } returns "Go"

        // Launch the Composable function with the navigation callback
        composeTestRule.setContent {
            SplashScreen(stringResourceProvider, navigationCallback = {})
        }

        // Assert that the SplashScreen is displayed
        composeTestRule.onNodeWithText("Go").assertIsDisplayed()
    }

    @Test
    fun testSplashScreen_startButtonClicked_navigationCallbackInvoked() {
        var navigationCallbackInvoked = false

        stringResourceProvider = StringResourceProviderImpl(mockResources)

        every { mockContext.getString(R.string.login_go, "")} returns "Go"
        every { stringResourceProvider.getString(R.string.login_go, "") } returns "Go"

        // Launch the Composable function with the navigation callback
        composeTestRule.setContent {
            DisneyMoviesTheme {
                SplashScreen(stringResourceProvider, navigationCallback = {
                    navigationCallbackInvoked = true
                })
            }
        }

        composeTestRule.onNodeWithText("Go").performClick()

        // Assert that the navigation callback was invoked
        assertTrue(navigationCallbackInvoked)
    }
}