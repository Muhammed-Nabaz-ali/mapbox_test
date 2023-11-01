package com.example.mapbox_test.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mapbox_test.R
import com.example.mapbox_test.presentation.screens.introduction.components.AuthButtons
import com.example.mapbox_test.ui.theme.BackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, testString: String?, testInt: Int?) {

//    val navControl = rememberNavController()
//    val navBackStackEntry by navControl.currentBackStackEntryAsState()
//    val args = navBackStackEntry?.arguments
//
//    val testInt = args?.getInt("testInt")
//    val testString = args?.getString("testString")

    Column(
        modifier = Modifier
            .background(BackgroundColor)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        Column(
            modifier = Modifier
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
                )
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Spacer(modifier = Modifier.height(60.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_cee_select_lang),
                contentDescription = null
            )
            Image(
                painter = painterResource(id = R.drawable.cee_text),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    modifier = Modifier.size(width = 110.dp, height = 50.dp),
                    trailingIcon = {
                        Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
                    },
                    value = "+964",
                    onValueChange = {},
                )

                Spacer(modifier = Modifier.width(6.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .size(width = 260.dp, height = 50.dp),
                    value = "750 730 4000",
                    onValueChange = {},

                    )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {},
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BackgroundColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(text = "$testInt $testString")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(thickness = 0.7.dp, modifier = Modifier.width(100.dp).padding(end = 5.dp))
                Text(text = "or continue with")
                Divider(thickness = 0.7.dp, modifier = Modifier.width(100.dp).padding(start = 5.dp))

            }

            Spacer(modifier = Modifier.height(35.dp))

            AuthButtons(
                text = "Browse as Guest",
                visible = false,
                painter = null,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(5.dp))
            AuthButtons(
                text = "Continue with Google",
                visible = true,
                painter = painterResource(id = R.drawable.ic_google_circle),
                onClick = {}
            )
            Spacer(modifier = Modifier.height(5.dp))
            AuthButtons(
                text = "Continue with Apple ID",
                visible = true,
                painter = painterResource(id = R.drawable.ic_apple1),
                onClick = {}
            )
        }
    }
}