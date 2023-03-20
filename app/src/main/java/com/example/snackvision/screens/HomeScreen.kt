package com.example.snackvision.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.snackvision.R
import com.example.snackvision.data.Meal
import com.example.snackvision.responseState.ResponseState
import com.example.snackvision.utils.Constants
import com.example.snackvision.viewModel.PopularCategory
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay


@Composable
fun HomeScreen() {

    val viewModel : PopularCategory = viewModel()
    HomeScr(responseState = viewModel.responseState)
}

@Composable
fun HomeScr(responseState: ResponseState=ResponseState.Unspecified){
    when(responseState){
        is ResponseState.Loading -> HomeScreenLoading()
        is ResponseState.Success ->HomeScreenSuccess(popularCategories = responseState.categories)
        is ResponseState.Error -> HomeErrorScreen()
        is ResponseState.Unspecified -> HomeScreenLoading()
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
//        // Outlined button
//        OutlinedButton(
//            onClick = {
//
//                Toast.makeText(context, "Will be implemented in future ", Toast.LENGTH_SHORT).show()
//
//            },
//            modifier = Modifier
//                .wrapContentSize()
//                .padding(10.dp),
//            border = BorderStroke(1.dp, Color.Blue),
//
//            ) {
//            Text(text = "Retry",
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black,
//                fontFamily = Constants.TextFontFamily
//            )
//        }

    }
}

@Composable
fun HomeScreenLoading() {
    Dialog(onDismissRequest = { }) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .padding(10.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 20.dp,
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    backgroundColor = Color.LightGray
                )
            }
        }

    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreenSuccess(popularCategories: List<Meal>) {
    val pagerState = rememberPagerState()


    Carousel(popularCategories, pagerState)

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            if (pagerState.currentPage == popularCategories.take(5).size - 1) {
                pagerState.animateScrollToPage(0)
            } else {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class, ExperimentalPagerApi::class)
private fun Carousel(popularCategories: List<Meal>, pagerState: PagerState)
{
    val height = LocalConfiguration.current.screenHeightDp.dp
    val width = LocalConfiguration.current.screenWidthDp.dp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFFececec)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            popularCategories.take(5).size,
            state = pagerState,
            modifier = Modifier
                .width(width)
                .height(height * 0.3f)
                .padding(10.dp)

        ) { page ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height * 0.3f),
                shape = RoundedCornerShape(10.dp)
            ) {
                GlideImage(
                    model = popularCategories[page].strMealThumb,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

            }

        }


        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 4.dp),
            indicatorShape = RectangleShape,
            indicatorHeight = 5.dp,
            spacing = 2.dp,
            indicatorWidth = 18.dp,
            activeColor = Color.Gray,
            inactiveColor = Color(0xFFFFA800)
        )
    }
}













