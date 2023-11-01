package com.example.mapbox_test.presentation.screens.introduction.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthButtons(
    text: String,
    onClick: () -> Unit,
    visible: Boolean,
    painter: Painter?,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        modifier = Modifier
//            .size(height = 60.dp, width = 230.dp)
            .border(width = 0.5.dp, color = Color.LightGray, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
    ) {
        if (visible) Image(painter = painter!!, contentDescription = null, modifier = Modifier.size(30.dp)) else null
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = text,
            color = Color.Black,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,

            )
    }
}