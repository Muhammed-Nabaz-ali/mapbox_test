package com.example.mapbox_test

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.ImageHolder
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.Style
import com.mapbox.maps.extension.compose.DefaultSettingsProvider
import com.mapbox.maps.extension.compose.DefaultSettingsProvider.createDefault2DPuck
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.style.expressions.dsl.generated.literal
import com.mapbox.maps.plugin.LocationPuck
import com.mapbox.maps.plugin.LocationPuck3D
import com.mapbox.maps.plugin.ModelScaleMode
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.compass.generated.CompassSettings
import com.mapbox.maps.plugin.gestures.generated.GesturesSettings
import com.mapbox.maps.plugin.locationcomponent.generated.LocationComponentSettings
import com.mapbox.maps.plugin.logo.generated.LogoSettings
import com.mapbox.maps.plugin.scalebar.ScaleBarPlugin
import com.mapbox.maps.plugin.scalebar.generated.ScaleBarSettings
import kotlin.math.roundToInt

@OptIn(MapboxExperimental::class, ExperimentalPermissionsApi::class)
@Composable
fun TestScreen(navController: NavController) {

    val context = LocalContext.current

    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    val speedState = remember { mutableStateOf(0f) }
    val speedUnits = "km/h"

    DisposableEffect(locationPermissionState) {
        var locationListener: LocationListener? = null

        if (!locationPermissionState.status.isGranted) {
            locationPermissionState.launchPermissionRequest()
        } else {

            val locationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationListener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    val speedKmph = location.speed * 3.6f // Convert to km/h
                    speedState.value = speedKmph
                }

                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
                override fun onProviderEnabled(provider: String) {}
                override fun onProviderDisabled(provider: String) {}
            }

            val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            if (hasGps) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        1000,  // Update interval in milliseconds
                        1f,  // Minimum distance to trigger updates (1 meter)
                        locationListener
                    )
                }
            }
        }

        onDispose {
            // Unregister the listener when the composable is disposed
            if (locationListener != null && locationPermissionState.status.isGranted) {
                val locationManager =
                    context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                locationManager.removeUpdates(locationListener)
            }
        }
    }


    val locationPuck3d = LocationPuck3D(
        modelUri = "assets://Cybertruck.gltf",
        modelScaleExpression = literal(listOf(100, 100, 100)).toJson(),
        modelRotation = listOf(0f, 0f, 90f),
        modelScaleMode = ModelScaleMode.VIEWPORT
    )

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

        Box(modifier = Modifier.fillMaxSize()) {

            MapboxMap(
                modifier = Modifier.fillMaxSize(),
                scaleBarSettings = ScaleBarSettings {
                    this.setEnabled(false)
                },
                compassSettings = CompassSettings {
                    this.setVisibility(true)
                },
                mapInitOptionsFactory = { context ->
                    MapInitOptions(
                        context = context,
                        styleUri = Style.STANDARD,
                        cameraOptions = CameraOptions.Builder()
                            .center(Point.fromLngLat(44.009583, 36.191301))
                            .zoom(11.0)
                            .pitch(50.0)
                            .build()
                    )
                },
                locationComponentSettings = LocationComponentSettings(
                    locationPuck = LocationPuck3D(
                        modelUri = "https://raw.githubusercontent.com/Muhammed-Nabaz-ali/mapbox_test/master/Cybertruck.gltf",
                        modelScaleExpression = literal(listOf(30, 30, 30)).toJson(),
                        modelRotation = listOf(0f, 0f, 90f),
                        modelScaleMode = ModelScaleMode.VIEWPORT,

                        )
                ) {
                    enabled = true
                    puckBearingEnabled = true
                    puckBearing = PuckBearing.COURSE
                },
                gesturesSettings = GesturesSettings {
                    this.doubleTapToZoomInEnabled = true
                },
                logoSettings = LogoSettings {
                    this.enabled = true
                },

            )

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                //top bar btns
                Row(
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 20.dp)
                        .fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {
                    Box(
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.bg_btn_top_nav_menu),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.shadow(
                                elevation = 7.dp,
                                shape = RoundedCornerShape(20.dp)
                            ),
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = null,
                            tint = Color(0xFF495CE8),
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                contentAlignment = Alignment.Center,
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.bg_btn_top_nav_menu),
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.shadow(
                                        elevation = 7.dp,
                                        shape = RoundedCornerShape(20.dp)
                                    ).background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.2f)),
                                            startY = 0f,
                                            endY = with(LocalDensity.current) { 10.dp.toPx() }
                                        )
                                    ),
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_no_ads),
                                    contentDescription = null,
                                    tint = Color(0xFF495CE8),
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "REMOVE ADS", fontSize = 8.sp, color = Color.Gray)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.bg_wether_view),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.shadow(
                                    elevation = 7.dp,
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_weather),
                                    contentDescription = null,
                                    tint = Color(0xFF495CE8),
                                    modifier = Modifier.size(28.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "26",
                                    color = Color.LightGray,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }

                //buttons and bottom sheet
                Column {

                    // 1st row button
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 0.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,

                        ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {


                        }

                        Box(
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.bg_btn_fab_my_location),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.shadow(
                                    elevation = 3.dp,
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_user_puck),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }

                    //second row buttons
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 15.dp, vertical = 10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,

                        ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.bg_btn_top_nav_menu),
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.shadow(
                                        elevation = 3.dp,
                                        shape = RoundedCornerShape(20.dp),

                                        ),
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_play),
                                    contentDescription = null,
                                    tint = Color(0xFF495CE8),
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "START TRIP",
                                fontSize = 8.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Box(
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.bg_btn_place_cam_fab_main_scr),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.shadow(
                                    elevation = 3.dp,
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_camera_fab),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }

                    // Box at the bottom
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(145.dp)
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)
                            )
                            .padding(horizontal = 20.dp, vertical = 35.dp)

                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_cee_two),
                                        contentDescription = null,
                                        tint = Color.Unspecified,
                                        modifier = Modifier.size(50.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "${speedState.value.roundToInt()}",
                                    color = Color.Black,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 26.sp
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = speedUnits,
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp
                                )
                            }

                            Spacer(modifier = Modifier.width(4.dp))
                            Row(
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(55.dp)
                                        .border(
                                            1.dp,
                                            Color(0xFFDDDDDD),
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .background(Color.Transparent),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_plus),
                                        contentDescription = null,
                                        tint = Color(0xFF495CE8),
                                        modifier = Modifier.size(32.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Box(
                                    modifier = Modifier
                                        .size(55.dp)
                                        .border(
                                            1.dp,
                                            Color(0xFFDDDDDD),
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .background(Color.Transparent),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_menu),
                                        contentDescription = null,
                                        tint = Color(0xFF495CE8),
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Box(
                                    modifier = Modifier
                                        .size(55.dp)
                                        .border(
                                            1.dp,
                                            Color(0xFFDDDDDD),
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .background(Color.Transparent),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_volume_slash),
                                        contentDescription = null,
                                        tint = Color(0xFF495CE8),
                                        modifier = Modifier.size(28.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

