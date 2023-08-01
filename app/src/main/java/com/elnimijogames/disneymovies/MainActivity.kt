package com.elnimijogames.disneymovies

 import android.os.Bundle
 import androidx.activity.ComponentActivity
 import androidx.activity.compose.setContent
 import androidx.compose.runtime.Composable
 import androidx.hilt.navigation.compose.hiltViewModel
 import androidx.lifecycle.viewmodel.compose.viewModel
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

    NavHost(navController, startDestination = "splash_screen") {
        composable(route = "splash_screen") {
            SplashScreen {
                navController.navigate("movie_list_screen")
            }
        }
        composable(route = "movie_list_screen") {
            MovieListScreen() { movieDetails ->
                navController.navigate("destination_movie_details_screen/${movieDetails.id}/${movieDetails.title}/${movieDetails.backdropPath}/${movieDetails.posterPath}/${movieDetails.originalLanguage}/${movieDetails.releaseDate}")
            }
        }
        composable(
            //route = "destination_movie_details_screen/{id}/{title}/{backdropPath}/{posterPath}/{originalLanguage}/{releaseDate}/{voteAverage}",
            route = "destination_movie_details_screen/{id}/{title}/{backdropPath}/{posterPath}/{originalLanguage}/{releaseDate}",
            arguments = listOf (
                navArgument("id") { type = NavType.IntType },
                navArgument("title") { type = NavType.StringType },
                navArgument("backdropPath") { type = NavType.StringType },
                navArgument("posterPath") { type = NavType.StringType },
                navArgument("originalLanguage") { type = NavType.StringType },
                navArgument("releaseDate") { type = NavType.StringType }
                //navArgument("voteAverage") { type = NavType.FloatType }
            )
        ) {
            val viewModel: MovieDetailsViewModel = hiltViewModel()
            MovieDetailsScreen(viewModel.movieDetailsState.value)
        }
//        composable(
//            route = "gallery_screen/{galleryPath}",
//            arguments = listOf (navArgument("galleryPath") {
//                type = NavType.StringType
//            })
//        ) {
//            val viewModel: GalleryScreenViewModel = hiltViewModel()
//            GalleryScreen(viewModel.detailsImagePath.value, navigateUpCallback = {
//                navController.popBackStack()
//            })
//        }
    }
}