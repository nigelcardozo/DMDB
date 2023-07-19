package com.elnimijogames.disneymovies

 import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
 import androidx.hilt.navigation.compose.hiltViewModel
 import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elnimijogames.disneymovies.model.StringResourceProviderImpl
 import com.elnimijogames.disneymovies.ui.movielist.MovieListScreen
 import com.elnimijogames.disneymovies.ui.movielist.MovieListViewModel
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
            val viewModel: MovieListViewModel = hiltViewModel()
            MovieListScreen(
                viewModel.movieDataState.value
//                viewModel.photoGalleryState.value,
//                viewModel.itemMenuState.value,
//                { menuId ->
//                    navController.navigate("details_screen/$menuId")
//                },
//                { galleryPath ->
//                    navController.navigate("gallery_screen/$galleryPath")
//                }
            )
        }
//        composable(
//            route = "details_screen/{menuId}",
//            arguments = listOf (navArgument("menuId") {
//                type = NavType.StringType
//            })
//        ) {
//            val viewModel: DetailsScreenViewModel = hiltViewModel()
//            DetailsScreen(viewModel.detailsTitle.value, viewModel.detailsImagePath.value, viewModel.detailsText.value)
//        }
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