package com.example.scheduler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.scheduler.ui.theme.SchedulerTheme

val subjects =
    listOf("Maths", "Science", "English", "History", "Geography", "Computer Science", "Art")

private lateinit var getGenerated: Array<Array<List<String>?>>

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchedulerTheme {
                val navController = rememberNavController()
                val title = remember { mutableStateOf("Monday") }

                // A surface container using the 'background' color from the theme
                getGenerated = emptyArray()
                getGenerated = Generator(subjects)
                Surface(
                    //modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(topBar = {
                        TopAppBar(title = { Text(text = title.value) },
                            actions = {})
                    },
                        bottomBar = {
                            val days = listOf(
                                DayScreens.Monday,
                                DayScreens.Tuesday,
                                DayScreens.Wednesday,
                                DayScreens.Thursday,
                                DayScreens.Friday,
                                DayScreens.Saturday
                            )
                            BottomNavigation {
                                val navBackStackentry by navController.currentBackStackEntryAsState()
                                val KEY_ROUTE = "android-support-nav:controller:route"
                                val currentRoute =
                                    navBackStackentry?.arguments?.getString(KEY_ROUTE)
                                days.forEach {
                                    BottomNavigationItem(
                                        selected = currentRoute == it.route,
                                        icon = { Icon(it.icon,null) },
                                        label = { Text(text = it.label) },
                                        onClick = {
                                            navController.popBackStack(
                                                navController.graph.displayName,
                                                false
                                            )
                                            if (currentRoute != it.route) {
                                                navController.navigate(it.route)
                                            }
                                        })
                                }
                            } // BottomNavigation
                        } // Bottombar
                    ) {
                        it
                        ScreenController(navController = navController, topBarTitle = title)
                    } // Scaffold
                }
            }
        }
    }
}

@Composable
fun ScreenController(
    navController: NavHostController,
    topBarTitle: MutableState<String>,
) {
    NavHost(navController = navController, startDestination = "Monday") {
        composable("Monday") {
            //Monday(mondayPeriods = subjects[0])
        }
    }
}

@Composable
fun Monday(mondayPeriods: Array<List<String>?>) {
    Text(text = mondayPeriods.toString(),
        style = TextStyle(color = Color.Black, fontSize = 20.sp),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize()
            //.background(Color.Cyan)
    )
}
