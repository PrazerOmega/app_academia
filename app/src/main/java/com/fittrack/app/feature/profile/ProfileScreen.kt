package com.fittrack.app.feature.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
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
fun ProfileScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.testTag("screen_profile"),
        topBar = { TopAppBar(title = { Text(stringResource(R.string.nav_profile)) }) },
    ) { padding ->
        EmptyState(
            title = stringResource(R.string.profile_placeholder_title),
            message = stringResource(R.string.profile_placeholder_message),
            icon = Icons.Filled.Person,
            modifier = Modifier.padding(padding),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0E0F11)
@Composable
private fun ProfileScreenPreview() {
    FitTrackTheme { ProfileScreen() }
}
