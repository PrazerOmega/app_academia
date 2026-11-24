package com.fittrack.app.feature.exercises

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fittrack.app.R
import com.fittrack.app.core.designsystem.component.EmptyState
import com.fittrack.app.core.designsystem.theme.FitTrackTheme

/** Placeholder da Fase 1. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExercisesScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.testTag("screen_exercises"),
        topBar = { TopAppBar(title = { Text(stringResource(R.string.nav_exercises)) }) },
    ) { padding ->
        EmptyState(
            title = stringResource(R.string.exercises_empty_title),
            message = stringResource(R.string.exercises_empty_message),
            icon = Icons.AutoMirrored.Filled.ListAlt,
            modifier = Modifier.padding(padding),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0E0F11)
@Composable
private fun ExercisesScreenPreview() {
    FitTrackTheme { ExercisesScreen() }
}
