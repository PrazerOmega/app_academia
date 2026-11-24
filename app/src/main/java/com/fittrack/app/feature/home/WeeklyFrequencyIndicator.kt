package com.fittrack.app.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fittrack.app.R
import com.fittrack.app.core.designsystem.theme.FitTrackTheme

/** 7 indicadores (seg–dom); preenchidos nos dias com sessão concluída. */
@Composable
fun WeeklyFrequencyIndicator(
    completedDays: List<Boolean>,
    modifier: Modifier = Modifier,
) {
    val shortNames = stringArrayResource(R.array.week_days_short)
    val fullNames = stringArrayResource(R.array.week_days_full)
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        completedDays.forEachIndexed { index, done ->
            val description = stringResource(
                if (done) R.string.home_frequency_day_done else R.string.home_frequency_day_not_done,
                fullNames[index],
            )
            val colors = MaterialTheme.colorScheme
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(36.dp)
                    .clearAndSetSemantics { contentDescription = description }
                    .then(
                        if (done) {
                            Modifier.background(colors.primary, CircleShape)
                        } else {
                            Modifier.border(1.dp, colors.outline, CircleShape)
                        },
                    ),
            ) {
                Text(
                    text = shortNames[index],
                    style = MaterialTheme.typography.labelLarge,
                    color = if (done) colors.onPrimary else colors.onSurfaceVariant,
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0E0F11)
@Composable
private fun WeeklyFrequencyIndicatorPreview() {
    FitTrackTheme {
        WeeklyFrequencyIndicator(listOf(true, false, true, false, true, false, false))
    }
}
