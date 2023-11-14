package com.example.mapbox_test.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mapbox_test.TestScreen
import com.example.mapbox_test.presentation.screens.SpeedometerTest
import com.example.mapbox_test.presentation.screens.auth.SignUpScreen
import com.example.mapbox_test.presentation.screens.introduction.InitialLanguageScreen
import com.example.mapbox_test.presentation.screens.introduction.OnBoardingScreen
import com.example.mapbox_test.presentation.screens.introduction.SplashScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {

    NavHost(
        navController = navController ,
        startDestination = Route.TestScreen.route,
    ){
        composable(
            route = Route.TestScreen.route
        ) {
            TestScreen(navController= navController)
        }
        composable(
            route = Route.SplashScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            }
        ) {
            SplashScreen(navController= navController)
        }
        composable(
            route = Route.InitialLanguageScreen.route
        ) {

            InitialLanguageScreen(navController= navController)
        }
        composable(
            route = Route.OnBoardingScreen.route
        ) {
            OnBoardingScreen(navController= navController)
        }
        composable(
            route = Route.SignUpScreen.route+"/{testString}/{testInt}",
            arguments = listOf(
                navArgument("testInt") {
                    type = NavType.IntType
                },
                navArgument("testString") {
                    type = NavType.StringType
                },
            )
        ) {
            val name = it.arguments?.getString("testString")
            val age = it.arguments?.getInt("testInt")
            SignUpScreen(navController= navController, testString = name, testInt = age)
        }
        composable(
            route = Route.SpeedometerTestScreen.route
        ) {
            SpeedometerTest(navController= navController)
        }

    }
}