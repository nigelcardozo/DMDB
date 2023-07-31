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
            MovieListScreen() { navigationMovieId ->
                navController.navigate("destination_movie_details_screen/$navigationMovieId")
            }
        }
        composable(
            route = "destination_movie_details_screen/{id}",
            arguments = listOf (navArgument("id") {
                type = NavType.StringType
            })
        ) {
            val viewModel: MovieDetailsViewModel = hiltViewModel()
            MovieDetailsScreen()
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