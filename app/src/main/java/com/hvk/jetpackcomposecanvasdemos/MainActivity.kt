package com.hvk.jetpackcomposecanvasdemos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.hvk.jetpackcomposecanvasdemos.components.Bottle
import com.hvk.jetpackcomposecanvasdemos.ui.theme.JetpackComposeCanvasDemosTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeCanvasDemosTheme {
                KoinContext {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        val navController = rememberNavController()
                        val navigator = koinInject<Navigator>()

                        ObserveAsEvents(flow = navigator.navigationActions) { action ->
                            when (action) {
                                is NavigationAction.Navigate -> navController.navigate(
                                    action.destination
                                ) {
                                    action.navOptions(this)
                                }

                                NavigationAction.NavigateUp -> navController.navigateUp()
                            }
                        }

                        NavHost(
                            navController = navController,
                            startDestination = navigator.startDestination,
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            navigation<Destination.HomeGraph>(
                                startDestination = Destination.HomeScreen
                            ) {
                                composable<Destination.HomeScreen> {
                                    val viewModel = koinViewModel<MainViewModel>()

//                                val args = it.toRoute<Destination.HomeScreen>()
                                    Column {
                                        Button(onClick = viewModel::navigateToBottle) {
                                            Text("Water Bottle")
                                        }
                                    }
                                }
                                composable<Destination.BottleScreen> {
                                    val viewModel = koinViewModel<BottleViewModel>()

//                                val args = it.toRoute<Destination.HomeScreen>()
                                    var usedWaterAmount by remember { mutableIntStateOf(400) }
                                    val totalWaterAmount = remember { 2400 }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color.Green),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Row(modifier = Modifier.fillMaxWidth()) {
                                            Spacer(modifier = Modifier.weight(1f, false))
                                            IconButton(onClick = viewModel::back) {
                                                Icon(
                                                    Icons.Default.Close,
                                                    contentDescription = "close icon"
                                                )
                                            }
                                        }
                                        Bottle(
                                            totalWaterAmount = totalWaterAmount,
                                            unit = "Litre",
                                            usedWaterAmount = usedWaterAmount
                                        )
                                        Spacer(modifier = Modifier.height(20.dp))
                                        Button(onClick = { usedWaterAmount += 100 }) {
                                            Text("drink...")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
