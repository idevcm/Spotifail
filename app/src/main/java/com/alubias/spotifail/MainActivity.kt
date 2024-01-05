package com.alubias.spotifail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alubias.spotifail.model.LoginModel
import com.alubias.spotifail.navigation.GrafoNavegacion
import com.alubias.spotifail.ui.theme.SpotifailTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpotifailTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val actividad = this@MainActivity
                    val loginModel: LoginModel by viewModels {
                        object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return LoginModel(application) as T
                            }
                        }
                    }
                    GrafoNavegacion(actividad, loginModel)
                }
            }
        }
    }
}