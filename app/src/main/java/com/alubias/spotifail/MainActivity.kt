package com.alubias.spotifail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alubias.spotifail.model.loginModel
import com.alubias.spotifail.navigation.GrafoNavegacion
import com.alubias.spotifail.ui.theme.SpotifailTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpotifailTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val actividad = this@MainActivity
                    val loginModel: loginModel = viewModel()
                    GrafoNavegacion(actividad, loginModel)
                }
            }
        }
    }
}