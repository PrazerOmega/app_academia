package com.fittrack.app.feature.home

import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Error(val message: String) : HomeUiState
    data class Success(val dashboard: HomeDashboard) : HomeUiState
}

/**
 * Dados do dashboard. Na Fase 1 só existe o estado vazio; a Fase 5 conecta aos dados reais
 * e poderá trocar estes tipos pelos modelos de domínio.
 */
data class HomeDashboard(
    val hasWorkouts: Boolean,
    val inProgressSession: InProgressSessionSummary?,
    val todayWorkouts: List<ScheduledWorkoutSummary>,
    val lastWorkout: LastWorkoutSummary?,
    val completedThisWeek: Int,
    val nextWorkout: NextWorkoutSummary?,
    /** 7 posições, segunda (índice 0) a domingo (índice 6). */
    val weeklyFrequency: List<Boolean>,
) {
    init {
        require(weeklyFrequency.size == DAYS_IN_WEEK) { "weeklyFrequency deve ter 7 posições" }
    }

    companion object {
        const val DAYS_IN_WEEK = 7

        val Empty = HomeDashboard(
            hasWorkouts = false,
            inProgressSession = null,
            todayWorkouts = emptyList(),
            lastWorkout = null,
            completedThisWeek = 0,
            nextWorkout = null,
            weeklyFrequency = List(DAYS_IN_WEEK) { false },
        )
    }
}

data class InProgressSessionSummary(val sessionId: Long, val workoutName: String, val startedAt: LocalTime)

data class ScheduledWorkoutSummary(val workoutId: Long, val name: String)

data class LastWorkoutSummary(val workoutName: String, val date: LocalDate, val duration: Duration)

data class NextWorkoutSummary(val workoutName: String, val dayOfWeek: DayOfWeek)
