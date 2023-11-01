package com.example.mapbox_test.navigation

sealed class Route(
    val route: String
) {
    object TestScreen : Route("testScreen")
    object SplashScreen : Route("splashScreen")
    object InitialLanguageScreen : Route("initialLanguageScreen")
    object OnBoardingScreen : Route("onBoardingScreen")
    object SignUpScreen : Route("signUpScreen")
    object SpeedometerTestScreen : Route("speedometerTestScreen")
}
