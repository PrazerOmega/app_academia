package com.fittrack.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fittrack.app.feature.exercises.ExercisesScreen
import com.fittrack.app.feature.history.HistoryScreen
import com.fittrack.app.feature.home.HomeScreen
import com.fittrack.app.feature.profile.ProfileScreen
import com.fittrack.app.feature.workouts.WorkoutsScreen

@Composable
fun FitTrackNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier,
    ) {
        composable<HomeRoute> {
            HomeScreen(
                onCreateWorkout = { navController.navigateToTopLevel(TopLevelDestination.WORKOUTS) },
                // Execução de treino chega na Fase 4.
                onResumeSession = {},
                onStartWorkout = {},
            )
        }
        composable<WorkoutsRoute> { WorkoutsScreen() }
        composable<ExercisesRoute> { ExercisesScreen() }
        composable<HistoryRoute> { HistoryScreen() }
        composable<ProfileRoute> { ProfileScreen() }
    }
}
