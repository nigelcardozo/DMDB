package com.elnimijogames.disneymovies.ui.splashscreen

import android.content.Context
import android.content.res.Resources
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.elnimijogames.disneymovies.R
import com.elnimijogames.disneymovies.model.StringResourceProvider
import com.elnimijogames.disneymovies.model.StringResourceProviderImpl
import com.elnimijogames.disneymovies.ui.theme.DisneyMoviesTheme
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    val mockContext = mockk<Context>(relaxed = true)
    val mockResources = mockk<Resources>(relaxed = true)

    var navigationCallbackInvoked = false

    @RelaxedMockK
    private lateinit var stringResourceProvider: StringResourceProvider

    @Before
    fun setup() {
        navigationCallbackInvoked = false

        stringResourceProvider = StringResourceProviderImpl(mockResources)

        every { mockContext.getString(R.string.login_go, "")} returns "Go"
        every { stringResourceProvider.getString(R.string.login_go, "") } returns "Go"

        composeTestRule.setContent {
            DisneyMoviesTheme {
                SplashScreen(stringResourceProvider, navigationCallback = {
                    navigationCallbackInvoked = true
                })
            }
        }
    }

    @Test
    fun clickStartButton_navigatesToNextScreen() {
        // Assert that the SplashScreen is displayed
        composeTestRule.onNodeWithText("Go").assertIsDisplayed()
    }

    @Test
    fun testSplashScreen_startButtonClicked_navigationCallbackInvoked() {
        assertFalse(navigationCallbackInvoked)

        composeTestRule.onNodeWithText("Go").performClick()

        // Assert that the navigation callback was invoked
        assertTrue(navigationCallbackInvoked)
    }
}