package com.example.followtheguidesdk.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.followtheguidesdk.ui.theme.FollowTheGuideSDKTheme
import com.mapfactor.sdk.MpfcEngine
import com.mapfactor.sdk.MpfcEngine.InitResult.*
import com.mapfactor.sdk.MpfcEngine.InitStatus.*
import com.mapfactor.sdk.map.MapControls
import com.mapfactor.sdk.map.MapView


@Composable
fun HomeScreen(
    innerPadding: PaddingValues,
    viewModel: HomeViewModel = viewModel()
){
    val uiState by viewModel.uiState.collectAsState()

    Column {

        Greeting(
            name = "Android Developer",
            modifier = Modifier.padding(innerPadding)
        )

        InitStatusText(status = uiState.sdkInitStatus)
        ResultInitStatusText(status = uiState.sdkInitResult)
        Text(text = "Device activation: ${uiState.sdkDeviceActivationResult?.name ?: "Not needed"}")

        when (uiState.sdkInitResult) {
            SUCCESS -> {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { context ->
                        MapView(context).apply {
                            //do whatever you want, e.g.

                            //handle map controls visibility
                            val mapControls = this.mapFragment.mapControls
                            mapControls.setMapControlVisibility(
                                MapControls.MapControl.COMPASS,
                                true
                            )

                            //or add listener
                            this.mapFragment.addOnMapReadyListener {

                            }
                        }
                    },
                    update = {

                    }
                )
            }

            else -> {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {

                    Text(text = "Init map here")

                }
            }
        }



    }
}


@Composable
fun InitStatusText(status: MpfcEngine.InitStatus) {
    val modifier =
        when (status) {
            NOT_INITIALIZED -> {
                Modifier.background(color = Color.LightGray)
            }
            IN_PROGRESS -> {
                Modifier.background(color = Color.Yellow)
            }
            FINISHED -> {
                Modifier.background(color = Color.Green)
            }
            RESTARTING -> {
                Modifier.background(color = Color.Transparent)
            }
        }


    Text(
        modifier = modifier,
        text = "Init status: $status",
    )
}


@Composable
fun ResultInitStatusText(status: MpfcEngine.InitResult) {

    val modifier =
    when (status) {
        NO_RESULT_YET -> {
            Modifier.background(color = Color.Yellow)
        }
        SUCCESS -> {
            Modifier.background(color = Color.Green)
        }
        else -> {
            Modifier.background(color = Color.Red)
        }
    }
    Text(
        modifier = modifier,
        text = "Init result: $status",
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    
    
    
    Column (modifier = modifier) {
        Text(
            text = "Hello $name!",
        )
        Text(
            text = "Welcome to Mapfactor SDK Sample!",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FollowTheGuideSDKTheme {
        Greeting("Android")
    }
}