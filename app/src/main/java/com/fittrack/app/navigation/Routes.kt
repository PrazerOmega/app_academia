package com.fittrack.app.navigation

import kotlinx.serialization.Serializable

// Rotas type-safe (Navigation Compose 2.8+). Todas as rotas do app ficam neste arquivo.

@Serializable
data object HomeRoute

@Serializable
data object WorkoutsRoute

@Serializable
data object ExercisesRoute

@Serializable
data object HistoryRoute

@Serializable
data object ProfileRoute
