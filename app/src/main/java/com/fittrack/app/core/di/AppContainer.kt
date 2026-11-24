package com.fittrack.app.core.di

import android.content.Context

/**
 * Container de dependências manual (sem Hilt/Koin).
 * Banco, DataStore e repositórios serão adicionados a partir da Fase 2.
 */
class AppContainer(
    @Suppress("unused") private val context: Context,
)
