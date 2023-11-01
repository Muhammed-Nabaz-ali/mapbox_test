package com.example.mapbox_test.presentation.screens.introduction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mapbox_test.R
import com.example.mapbox_test.navigation.Route
import com.example.mapbox_test.presentation.screens.introduction.components.SelectLangButton
import com.example.mapbox_test.ui.theme.BackgroundColor
import com.example.mapbox_test.ui.theme.Bahij
import com.example.mapbox_test.ui.theme.Work
import kotlinx.coroutines.delay

@Composable
fun InitialLanguageScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .background(BackgroundColor)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_cee_select_lang),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(30.dp))

        Column {

            SelectLangButton(
                text = "English",
                fontFamily = Work,
                onClick = {

                    val testString = "test string passing works"
                    val testInt = 23

//                    navController.navigate("${Route.InitialLanguageScreen.route}/${testString}/${testInt}")

                    navController.navigate("${Route.SignUpScreen.route}/${testString}/${testInt}")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            SelectLangButton(
                text = "Turkish",
                fontFamily = Work,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            SelectLangButton(
                text = "عربي",
                fontFamily = Bahij,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            SelectLangButton(
                text = "کوردی سۆرانی",
                fontFamily = Bahij,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

