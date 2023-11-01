package com.example.mapbox_test.presentation.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mapbox_test.ui.theme.BackgroundColor
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun SpeedometerTest(navController: NavController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.size(300.dp)) {
            Canvas(
                modifier = Modifier.fillMaxSize().background(Color.LightGray)
            ) {
                val canvasWidth = size.width
                val canvasHeight = size.height

//                val path = Path().apply {
//                    moveTo(50f, 250f)
//                    cubicTo(50f, 50f, 250f, 50f, 250f, 250f)
//                }
//                drawLine(
//                    start = Offset(x = 0f, y = 0f),
//                    end = Offset(x = 0f, y = canvasHeight),
//                    color = Color.Blue
//                )

                val pathtest = Path()
                pathtest.moveTo(0f, 0f)
                pathtest.lineTo(size.width / 2f, size.height / 2f)
                pathtest.lineTo(size.width, size.height)
                pathtest.close()
                    drawPath(pathtest, Color.Magenta, style = Stroke(width = 1f))

//                drawPath(
//                    path = path,
//                    color = Color.Red,
//                    style =Stroke(75f),
//                    alpha = 1f,
//                )


//                val radius = size.width * .5f
//                val angleDegreeDifference = 6.545
//                (0..62).forEach {
//                    val angleRadDifference =
//                        (((angleDegreeDifference * it) - 355f) * (Math.PI / 180f)).toFloat()
//                    if (it >= 19) {
//                        val x =
//                            (radius - ((radius * .05f) / 2)) * cos(angleRadDifference) + size.center.x
//                        val y =
//                            (radius - ((radius * .05f) / 2)) * sin(angleRadDifference) + size.center.y
//                        val i = it - 19
//                        val col = Color(
//                            red = (73 + (i * 3.744)).toInt(),
//                            green = (92 - (i * 0.325)).toInt(),
//                            blue = (232 - (i * 4.186)).toInt()
//                        )
//
//                        drawCircle(
//                            color = col,
//                            center = Offset(x, y),
//                            radius = 5f
//                        )
//                    }
//                }
//                drawArc(
//                    brush = Brush.linearGradient(
//                        colors = listOf(
//                            Color(0xFFECEEFD),
//                            Color(0xFFECEEFD),
//                        )
//                    ),
//                    startAngle = 127.7f,
//                    sweepAngle = 285 * 1f,
//                    useCenter = false,
//                    style = Stroke(
//                        6.dp.toPx(),
//                        cap = StrokeCap.Round,
//                    ),
//                    size = size * 0.85f,
//                    topLeft = Offset(size.width * 0.075f, size.width * 0.075f)
//                )
//                val angleInDegrees = (285 * 0.22f) + (55.0 - (0.22f * 20))
//                val radiuss = (size.height * 0.85 / 2)
//                val x =
//                    -(radiuss * sin(Math.toRadians(angleInDegrees))).toFloat() + (size.width / 2)
//                val y =
//                    (radiuss * cos(Math.toRadians(angleInDegrees))).toFloat() + (size.height / 2)
//                drawArc(
//                    brush = Brush.linearGradient(
//                        start = Offset(x, y),
//                        end = Offset(180f, 690f),
//                        colors = listOf(
//                            Color(0xFFEA4E34),
//                            Color(0xFF495CE8),
//                        ),
//                        tileMode = TileMode.Mirror
//                    ),
//                    startAngle = 127.7f,
//                    sweepAngle = 285 * 0.22f,
//                    useCenter = false,
//                    style = Stroke(
//                        14.dp.toPx(),
//                        cap = StrokeCap.Round,
//                    ),
//                    size = size * 0.85f,
//                    topLeft = Offset(size.width * 0.075f, size.width * 0.075f)
//                )

            }
        }

    }
}