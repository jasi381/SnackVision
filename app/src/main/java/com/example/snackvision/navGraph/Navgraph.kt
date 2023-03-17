package com.example.snackvision.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.snackvision.screens.HomeScreen
import com.example.snackvision.screens.Screens
import com.example.snackvision.screens.SplashScreen

@Composable
fun SetUpNavGraph(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ){
        composable(route = Screens.SplashScreen.route){
            SplashScreen(navController = navController)
        }

        composable(route = Screens.HomeScreen.route){
            HomeScreen()
        }
    }
}