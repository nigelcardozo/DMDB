package com.elnimijogames.disneymovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.elnimijogames.disneymovies.model.StringResourceProviderImpl
import com.elnimijogames.disneymovies.ui.detailsscreen.MovieDetailsScreen
import com.elnimijogames.disneymovies.ui.detailsscreen.MovieDetailsViewModel
import com.elnimijogames.disneymovies.ui.movielist.MovieListScreen
import com.elnimijogames.disneymovies.ui.splashscreen.SplashScreen
import com.elnimijogames.disneymovies.ui.theme.DisneyMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

private var navigatedToMovieDetailsScreen = false

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val stringResourceProvider: StringResourceProviderImpl = StringResourceProviderImpl(resources)

        setContent {
            DisneyMoviesTheme {
                DMDBApp(stringResourceProvider)
            }
        }
    }
}

@Composable
private fun DMDBApp(stringResourceProvider: StringResourceProviderImpl) {
    val navController = rememberNavController()
    navController.enableOnBackPressed(true)

    NavHost(navController, startDestination = "splash_screen") {
        composable(
            route = "splash_screen",
            enterTransition = {
                navigatedToMovieDetailsScreen = false
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            }) {
            SplashScreen(stringResourceProvider) {
                navController.navigate("movie_list_screen")
            }
        }
        composable(
            route = "movie_list_screen",
            enterTransition = {
                //if (("splash_screen").equals(navController.previousBackStackEntry?.destination?.route)) {
                if (!navigatedToMovieDetailsScreen) {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                        animationSpec = tween(500)
                    )
                }
                else {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                        animationSpec = tween(500)
                    )
                }
            },
            exitTransition = {
                if (("movie_list_screen") == navController.previousBackStackEntry?.destination?.route) {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                        animationSpec = tween(500)
                    )
                } else {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                        animationSpec = tween(500)
                    )
                }

            }) {
            MovieListScreen() { id ->
                navController.navigate("destination_movie_details_screen/${id}")
            }
        }
        composable(
            route = "destination_movie_details_screen/{id}",
            arguments = listOf (
                navArgument("id") { type = NavType.IntType }
            ),
            enterTransition = {
                navigatedToMovieDetailsScreen = true
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            val viewModel: MovieDetailsViewModel = hiltViewModel()
            MovieDetailsScreen(
                viewModel.movieDetailsState.value
            )
        }
    }
}