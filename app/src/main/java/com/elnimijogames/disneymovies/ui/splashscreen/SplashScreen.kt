package com.elnimijogames.disneymovies.ui.splashscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.elnimijogames.disneymovies.R
import com.elnimijogames.disneymovies.model.StringResourceProvider
import com.elnimijogames.disneymovies.ui.theme.SplashGradientEnd
import com.elnimijogames.disneymovies.ui.theme.SplashGradientStart
import com.elnimijogames.disneymovies.ui.theme.StoryTimeBlue

@Composable
fun SplashScreen(stringResourceProvider: StringResourceProvider, navigationCallback: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        SplashGradientStart,
                        SplashGradientEnd
                    )
                )
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Logo()
        StartButton(stringResourceProvider, navigationCallback)
    }
}

@Composable
fun StartButton(stringResourceProvider:StringResourceProvider, navigationCallback: () -> Unit) {
    Button(
        onClick = {
            navigationCallback()
        },
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = StoryTimeBlue
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 100.dp, start = 20.dp, end = 20.dp)
    ) {
        // ToDo - Replace this with an injected resource handler to use within the viewmodel.
        Text(text = stringResourceProvider.getString(R.string.login_go, ""))
    }
}

@Composable
fun Logo() {
    AsyncImage(
        model = "file:///android_asset/images/disney_logo.png",
        contentDescription = "Porsche Wallpaper",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.9f)
            .padding(
                start = 20.dp,
                top = 0.dp,
                bottom = 300.dp,
                end = 20.dp
            )
    )
}

//@Preview(showBackground = true)
//@Composable
//fun SplashScreenPreview() {
//    DisneyMoviesTheme() {
//        SplashScreen(StringResourceProvider(Resources()), {})
//    }
//}