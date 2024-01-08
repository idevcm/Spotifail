package com.alubias.spotifail.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alubias.spotifail.R

    @Composable
    fun BuscadorScreen(loginModel: LoginModel, navController: NavController){

        DisposableEffect(key1 = navController.currentBackStackEntry) {
            onDispose {
                loginModel.stopSong()
                loginModel.resetMediaPlayer()
            }
        }

        LaunchedEffect(key1 = true) {
            loginModel.startSong(R.raw.teriyakii)
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.meme2),
                contentDescription = "Image 2",
                contentScale = ContentScale.Fit,
                modifier = Modifier.padding(12.dp).fillMaxSize()
            )
        }
    }
