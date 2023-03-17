package com.example.snackvision.screens

sealed class Screens(val route:String){
    object SplashScreen: Screens("splash_screen")
    object HomeScreen: Screens("home_screen")
}
