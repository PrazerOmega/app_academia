package com.fittrack.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fittrack.app.core.designsystem.theme.FitTrackTheme
import com.fittrack.app.navigation.FitTrackApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // O tema virá do DataStore na fase de Perfil; escuro é o padrão.
            FitTrackTheme(darkTheme = true) {
                FitTrackApp()
            }
        }
    }
}
