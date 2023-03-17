package com.example.snackvision.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import com.example.snackvision.R
import com.example.snackvision.utils.Constants
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Constants.splashScreenBackgroundColor)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Constants.splashScreenBackgroundColor)
    ) {

        val text = remember { Animatable(initialValue = 0f) }
        LaunchedEffect(Unit) {
            text.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 3000, delayMillis = 300)
            )
        }

        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.splash_screen)
        )
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp

        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.7f)
                .padding(
                    end = 15.dp,
                    start = 15.dp,
                    top = 15.dp,
                    bottom = 10.dp
                )
        )

        Text(
            text = stringResource(id = R.string.headLine),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Gray
                .copy(alpha = text.value),
            textAlign = TextAlign.Center,
            fontFamily =Constants.TextFontFamily,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, bottom = 15.dp)
        )


    }
    LaunchedEffect(Unit) {
        delay(4500)
        navController.navigate(Screens.HomeScreen.route){
            popUpTo(Screens.SplashScreen.route){inclusive = true}
        }
    }

}







