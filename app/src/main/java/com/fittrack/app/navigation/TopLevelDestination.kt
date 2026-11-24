package com.fittrack.app.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.automirrored.outlined.ListAlt
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.fittrack.app.R
import kotlin.reflect.KClass

/** As 5 abas da barra de navegação inferior, na ordem em que aparecem. */
enum class TopLevelDestination(
    val route: Any,
    val routeClass: KClass<*>,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @param:StringRes val labelRes: Int,
) {
    HOME(HomeRoute, HomeRoute::class, Icons.Filled.Home, Icons.Outlined.Home, R.string.nav_home),
    WORKOUTS(
        WorkoutsRoute, WorkoutsRoute::class,
        Icons.Filled.FitnessCenter, Icons.Outlined.FitnessCenter, R.string.nav_workouts,
    ),
    EXERCISES(
        ExercisesRoute, ExercisesRoute::class,
        Icons.AutoMirrored.Filled.ListAlt, Icons.AutoMirrored.Outlined.ListAlt, R.string.nav_exercises,
    ),
    HISTORY(HistoryRoute, HistoryRoute::class, Icons.Filled.History, Icons.Outlined.History, R.string.nav_history),
    PROFILE(ProfileRoute, ProfileRoute::class, Icons.Filled.Person, Icons.Outlined.Person, R.string.nav_profile),
}
