package com.example.snackvision.screens

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.*
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.snackvision.R
import com.example.snackvision.data.Meal
import com.example.snackvision.responseState.ResponseState
import com.example.snackvision.utils.Constants
import com.example.snackvision.viewModel.PopularCategory


@Composable
fun HomeScreen() {

    val viewModel : PopularCategory = viewModel()
    HomeScr(responseState = viewModel.responseState)
}

@Composable
fun HomeScr(responseState: ResponseState?=null){
    when(responseState){
        is ResponseState.Loading -> HomeScreenLoading()
        is ResponseState.Success -> HomeScreenSuccess(popularCategories = responseState.categories)
        is ResponseState.Error -> HomeErrorScreen()
        null -> HomeErrorScreen()
    }
}

@Composable
fun HomeErrorScreen() {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.error)
        )
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp

        val context = LocalContext.current

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
        Text(text = "Something Went Wrong",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red,
            fontFamily = Constants.TextFontFamily
        )
        // Outlined button
        OutlinedButton(
            onClick = {

                Toast.makeText(context, "Will be implemented in future ", Toast.LENGTH_SHORT).show()

            },
            modifier = Modifier
                .wrapContentSize()
                .padding(10.dp),
            border = BorderStroke(1.dp, Color.Blue),

        ) {
            Text(text = "Retry",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = Constants.TextFontFamily
            )
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeScreenSuccess(popularCategories: List<Meal>) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        contentPadding = PaddingValues(10.dp)
    ) {
         items(
             popularCategories.take(10)
         ){
             GlideImage(
                 model = it.strMealThumb,
                 contentDescription = null
             )
             Spacer(modifier = Modifier.height(10.dp))
         }
     }
}

@Composable
fun HomeScreenLoading() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        contentAlignment = Alignment.Center) {
        CircularProgressIndicator()

    }
}
