package com.elnimijogames.disneymovies.ui.detailsscreen

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elnimijogames.disneymovies.MainActivity
import com.elnimijogames.disneymovies.di.AppModule
import com.elnimijogames.disneymovies.testinghelpers.dummydata.DummyMovieDetails
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
class MovieDetailsScreenTest {

    @get:Rule (order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule (order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    var navigationCallbackInvoked = false
    val dummyMovieDetailsResponse = DummyMovieDetails().getDummyMovieDetailsResponse()

    @Before
    fun setup() {
        hiltRule.inject()

        navigationCallbackInvoked = false

        composeRule.activity.setContent {

            val navController = rememberNavController()

            DisneyMoviesTheme {
                NavHost(navController, startDestination = "movie_details_screen") {
                    composable(route = "movie_details_screen") {
                        MovieDetailsScreen(movieDetailsResponse = dummyMovieDetailsResponse)
                    }
                }
            }
        }
    }

    @Test
    fun testMovieDetailsScreen_checkHeadlineCardTextIsCorrectlyDisplayed() {
        composeRule.onNodeWithTag("md_headline_card_movie_title").assertTextEquals(dummyMovieDetailsResponse.title.toString())
        composeRule.onNodeWithTag("md_headline_card_vote_average").assertTextEquals(dummyMovieDetailsResponse.voteAverage?.toInt().toString())
        composeRule.onNodeWithTag("md_headline_card_production_companies").assertTextEquals(dummyMovieDetailsResponse.productionCompanies[0].name.toString())
        composeRule.onNodeWithTag("md_headline_card_release_date").assertTextEquals(dummyMovieDetailsResponse.releaseDate.toString() + "   (102 min)")

        composeRule.onNodeWithTag("md_headline_card_icon_expand_more").assertIsDisplayed()
        composeRule.onNodeWithTag("md_headline_card_icon_expand_less").assertDoesNotExist()
        composeRule.onNodeWithTag("md_headline_card_budget").assertDoesNotExist()
        composeRule.onNodeWithTag("md_headline_card_revenue").assertDoesNotExist()
    }

    @Test
    fun testMovieDetailsScreen_checkHeadlineCardExpansion() {
        composeRule.onNodeWithTag("md_headline_card_icon_expand_more").assertIsDisplayed()
        composeRule.onNodeWithTag("md_headline_card_icon_expand_less").assertDoesNotExist()
        composeRule.onNodeWithTag("md_headline_card_budget").assertDoesNotExist()
        composeRule.onNodeWithTag("md_headline_card_revenue").assertDoesNotExist()

        composeRule.onNodeWithTag("md_headline_card_icon_expand_more").performClick()
        composeRule.waitForIdle()

        composeRule.onNodeWithTag("md_headline_card_icon_expand_less").assertIsDisplayed()
        composeRule.onNodeWithTag("md_headline_card_icon_expand_more").assertDoesNotExist()

        composeRule.onNodeWithTag("md_headline_card_movie_title").assertTextEquals(dummyMovieDetailsResponse.title.toString())
        composeRule.onNodeWithTag("md_headline_card_vote_average").assertTextEquals(dummyMovieDetailsResponse.voteAverage?.toInt().toString())
        composeRule.onNodeWithTag("md_headline_card_production_companies").assertTextEquals(dummyMovieDetailsResponse.productionCompanies[0].name.toString())
        composeRule.onNodeWithTag("md_headline_card_release_date").assertTextEquals(dummyMovieDetailsResponse.releaseDate.toString() + "   (102 min)")

        composeRule.onNodeWithTag("md_headline_card_budget").assertTextEquals("Budget: " + dummyMovieDetailsResponse.budget.toString())
        composeRule.onNodeWithTag("md_headline_card_revenue").assertTextEquals("Revenue: " + dummyMovieDetailsResponse.revenue.toString())
    }

    @Test
    fun testMovieDetailsScreen_checkBaseCardsTextIsCorrectlyDisplayed() {
        composeRule.onNodeWithTag("md_overview_card_overview", useUnmergedTree = true).assertTextEquals(dummyMovieDetailsResponse.overview.toString())
        composeRule.onNodeWithTag("md_tags_card").assertTextEquals("Animation, Comedy, Family, Fantasy")
    }
}