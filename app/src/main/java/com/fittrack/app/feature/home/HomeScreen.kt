package com.fittrack.app.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fittrack.app.R
import com.fittrack.app.core.designsystem.component.EmptyState
import com.fittrack.app.core.designsystem.component.ErrorState
import com.fittrack.app.core.designsystem.component.FitTrackCard
import com.fittrack.app.core.designsystem.component.LoadingState
import com.fittrack.app.core.designsystem.theme.FitTrackTheme
import com.fittrack.app.core.designsystem.theme.Spacing
import com.fittrack.app.core.util.formatDuration
import com.fittrack.app.core.util.formatShortDate
import com.fittrack.app.core.util.formatTime
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun HomeScreen(
    onCreateWorkout: () -> Unit,
    onResumeSession: (sessionId: Long) -> Unit,
    onStartWorkout: (workoutId: Long) -> Unit,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeContent(
        uiState = uiState,
        onCreateWorkout = onCreateWorkout,
        onResumeSession = onResumeSession,
        onStartWorkout = onStartWorkout,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    uiState: HomeUiState,
    onCreateWorkout: () -> Unit,
    onResumeSession: (sessionId: Long) -> Unit,
    onStartWorkout: (workoutId: Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.testTag("screen_home"),
        topBar = { TopAppBar(title = { Text(stringResource(R.string.home_title)) }) },
    ) { padding ->
        when (uiState) {
            HomeUiState.Loading -> LoadingState(Modifier.padding(padding))
            is HomeUiState.Error -> ErrorState(uiState.message, Modifier.padding(padding))
            is HomeUiState.Success -> DashboardList(
                dashboard = uiState.dashboard,
                contentPadding = padding,
                onCreateWorkout = onCreateWorkout,
                onResumeSession = onResumeSession,
                onStartWorkout = onStartWorkout,
            )
        }
    }
}

@Composable
private fun DashboardList(
    dashboard: HomeDashboard,
    contentPadding: PaddingValues,
    onCreateWorkout: () -> Unit,
    onResumeSession: (Long) -> Unit,
    onStartWorkout: (Long) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
        contentPadding = PaddingValues(Spacing.md),
        verticalArrangement = Arrangement.spacedBy(Spacing.md),
    ) {
        dashboard.inProgressSession?.let { session ->
            item(key = "in_progress") { InProgressCard(session, onResumeSession) }
        }
        if (!dashboard.hasWorkouts) {
            item(key = "empty") {
                EmptyState(
                    title = stringResource(R.string.home_empty_title),
                    message = stringResource(R.string.home_empty_message),
                    actionLabel = stringResource(R.string.home_empty_action),
                    onAction = onCreateWorkout,
                )
            }
        } else {
            item(key = "today") {
                TodayCard(dashboard.todayWorkouts, onStartWorkout, startEnabled = dashboard.inProgressSession == null)
            }
        }
        item(key = "last") { LastWorkoutCard(dashboard.lastWorkout) }
        item(key = "week") { WeekCard(dashboard.completedThisWeek, dashboard.weeklyFrequency) }
        if (dashboard.hasWorkouts) {
            item(key = "next") { NextWorkoutCard(dashboard.nextWorkout) }
        }
    }
}

@Composable
private fun InProgressCard(session: InProgressSessionSummary, onResume: (Long) -> Unit) {
    FitTrackCard(
        title = stringResource(R.string.home_in_progress_title),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) {
        Text(session.workoutName, style = MaterialTheme.typography.titleLarge)
        Text(
            stringResource(R.string.home_in_progress_started_at, formatTime(session.startedAt)),
            style = MaterialTheme.typography.bodyMedium,
        )
        Button(
            onClick = { onResume(session.sessionId) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Spacing.md),
        ) {
            Icon(Icons.Filled.PlayArrow, contentDescription = null)
            Text(stringResource(R.string.home_action_resume), Modifier.padding(start = Spacing.sm))
        }
    }
}

@Composable
private fun TodayCard(
    workouts: List<ScheduledWorkoutSummary>,
    onStart: (Long) -> Unit,
    startEnabled: Boolean,
) {
    FitTrackCard(title = stringResource(R.string.home_today_title)) {
        if (workouts.isEmpty()) {
            SecondaryText(stringResource(R.string.home_today_empty))
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.sm)) {
                workouts.forEach { workout ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            workout.name,
                            style = MaterialTheme.typography.bodyLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f),
                        )
                        FilledTonalButton(
                            onClick = { onStart(workout.workoutId) },
                            enabled = startEnabled,
                        ) { Text(stringResource(R.string.home_action_start)) }
                    }
                }
            }
        }
    }
}

@Composable
private fun LastWorkoutCard(last: LastWorkoutSummary?) {
    FitTrackCard(title = stringResource(R.string.home_last_workout_title)) {
        if (last == null) {
            SecondaryText(stringResource(R.string.home_last_workout_empty))
        } else {
            Text(last.workoutName, style = MaterialTheme.typography.bodyLarge)
            SecondaryText(
                stringResource(
                    R.string.home_last_workout_details,
                    formatShortDate(last.date),
                    formatDuration(last.duration),
                ),
            )
        }
    }
}

@Composable
private fun WeekCard(completed: Int, frequency: List<Boolean>) {
    FitTrackCard(title = stringResource(R.string.home_week_title)) {
        Text(
            pluralStringResource(R.plurals.home_week_completed, completed, completed),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = Spacing.md),
        )
        WeeklyFrequencyIndicator(frequency)
    }
}

@Composable
private fun NextWorkoutCard(next: NextWorkoutSummary?) {
    val dayNames = stringArrayResource(R.array.week_days_full)
    FitTrackCard(title = stringResource(R.string.home_next_workout_title)) {
        if (next == null) {
            SecondaryText(stringResource(R.string.home_next_workout_empty))
        } else {
            Text(
                stringResource(
                    R.string.home_next_workout_details,
                    next.workoutName,
                    dayNames[next.dayOfWeek.value - 1],
                ),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
private fun SecondaryText(text: String) {
    Text(text, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
}

@Preview(name = "Vazio", showBackground = true, backgroundColor = 0xFF0E0F11)
@Composable
private fun HomeEmptyPreview() {
    FitTrackTheme {
        HomeContent(HomeUiState.Success(HomeDashboard.Empty), {}, {}, {})
    }
}

@Preview(name = "Com dados", showBackground = true, backgroundColor = 0xFF0E0F11, heightDp = 1000)
@Composable
private fun HomeFilledPreview() {
    FitTrackTheme {
        HomeContent(
            uiState = HomeUiState.Success(
                HomeDashboard(
                    hasWorkouts = true,
                    inProgressSession = InProgressSessionSummary(1, "Treino A — Peito e Tríceps", LocalTime.of(18, 5)),
                    todayWorkouts = listOf(ScheduledWorkoutSummary(1, "Treino A — Peito e Tríceps")),
                    lastWorkout = LastWorkoutSummary("Treino C — Pernas", LocalDate.of(2026, 11, 21), Duration.ofMinutes(64)),
                    completedThisWeek = 2,
                    nextWorkout = NextWorkoutSummary("Treino B — Costas e Bíceps", DayOfWeek.TUESDAY),
                    weeklyFrequency = listOf(true, false, true, false, false, false, false),
                ),
            ),
            onCreateWorkout = {},
            onResumeSession = {},
            onStartWorkout = {},
        )
    }
}

@Preview(name = "Carregando", showBackground = true, backgroundColor = 0xFF0E0F11)
@Composable
private fun HomeLoadingPreview() {
    FitTrackTheme { HomeContent(HomeUiState.Loading, {}, {}, {}) }
}

@Preview(name = "Claro", showBackground = true)
@Composable
private fun HomeLightPreview() {
    FitTrackTheme(darkTheme = false) {
        HomeContent(HomeUiState.Success(HomeDashboard.Empty), {}, {}, {})
    }
}
