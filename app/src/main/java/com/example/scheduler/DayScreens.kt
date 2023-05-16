package com.example.scheduler

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face2
import androidx.compose.material.icons.filled.Face3
import androidx.compose.material.icons.filled.Face4
import androidx.compose.material.icons.filled.Face5
import androidx.compose.material.icons.filled.Face6
import androidx.compose.material.icons.filled.LooksOne
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DayScreens(val route: String, val label: String, val icon: ImageVector) {
    object Monday: DayScreens("Monday","Monday", Icons.Default.LooksOne)
    object Tuesday: DayScreens("Tuesday","Tuesday",Icons.Default.Face2)
    object Wednesday: DayScreens("Wednesday","Wednesday",Icons.Default.Face3)
    object Thursday: DayScreens("Thursday","Thursday",Icons.Default.Face4)
    object Friday: DayScreens("Friday","Friday",Icons.Default.Face5)
    object Saturday: DayScreens("Saturday","Saturday",Icons.Default.Face6)
}
