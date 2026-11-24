package com.fittrack.app.core.util

import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private val PT_BR: Locale = Locale.forLanguageTag("pt-BR")
private val SHORT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy", PT_BR)
private val TIME = DateTimeFormatter.ofPattern("HH:mm", PT_BR)

/** Formata uma duração como "45 min" ou "1 h 05 min". Durações negativas viram "0 min". */
fun formatDuration(duration: Duration): String {
    val totalMinutes = duration.toMinutes().coerceAtLeast(0)
    val hours = totalMinutes / 60
    val minutes = totalMinutes % 60
    return if (hours == 0L) "$minutes min" else "$hours h %02d min".format(minutes)
}

fun formatShortDate(date: LocalDate): String = date.format(SHORT_DATE)

fun formatTime(time: LocalTime): String = time.format(TIME)
