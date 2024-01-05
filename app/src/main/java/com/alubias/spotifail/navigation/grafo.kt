package com.alubias.spotifail.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alubias.spotifail.MainActivity
import com.alubias.spotifail.jukebox.Responsive
import com.alubias.spotifail.model.LoginModel
import com.alubias.spotifail.scaffold.MyScaffold


@Composable
fun GrafoNavegacion(actividad: MainActivity, loginModel: LoginModel) {
    val navController = rememberNavController()

    MyScaffold {
        NavHost(navController = navController, startDestination = Rutas.MainActivity.ruta) {
            composable(Rutas.MainActivity.ruta) {
                Responsive(actividad = actividad, navController, loginModel)
            }
        }
    }
}
