package com.alubias.spotifail.navigation

sealed class Rutas (val ruta: String){
    object MainActivity:Rutas("MainActivity")
    object Cover:Rutas("Cover")
    object Listas:Rutas("Listas")
    object Buscador:Rutas("Buscador")
}