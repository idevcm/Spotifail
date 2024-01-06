package com.alubias.spotifail.model

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alubias.spotifail.navigation.Rutas
import com.alubias.spotifail.ui.theme.MyColors

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CoverScreen(navController: NavHostController) {
    val myColors = MyColors()
    val arrayMyColor = myColors.colorList

    val animation = rememberInfiniteTransition(label = "")
    val colorAnimation by animation.animateColor(
        initialValue = arrayMyColor[3],
        targetValue = Color.Yellow,
        animationSpec = infiniteRepeatable(
            animation = tween(400),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    val gradient = Brush.linearGradient(
        0.0f to arrayMyColor[0],
        500.0f to colorAnimation,
        start = Offset.Zero,
        end = Offset.Infinite)

    Box (modifier = Modifier.background(gradient)){
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Logo()
            Description()
            StartButton(navController)
        }
    }
}

@Composable
fun Logo(){
    val myColors = MyColors()
    val arrayMyColor = myColors.colorList

    val transition = rememberInfiniteTransition(label = "")
    val rotation by transition.animateFloat(
        initialValue = 5f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "S",
            fontWeight = FontWeight.Bold,
            fontSize = 66.sp,
            color = arrayMyColor[2],
            modifier = Modifier
                .padding(2.dp)
                .rotate(-rotation)
                .offset(4.dp, 6.dp)
        )
        Text(
            text = "POTIFAI",
            fontWeight = FontWeight.Bold,
            fontSize = 56.sp,
            color = arrayMyColor[2],
            modifier = Modifier.padding(2.dp)
        )
        Text(
            text = "L",
            fontWeight = FontWeight.Bold,
            fontSize = 56.sp,
            color = arrayMyColor[2],
            modifier = Modifier
                .padding(2.dp)
                .rotate(rotation + 80)
                .offset(18.dp)
        )
    }
}


@Composable
fun Description(){
    val myColors = MyColors()
    val arrayMyColor = myColors.colorList
    Text(
        text = "Welcome to spoifail,\n a place with... *erm*\n ...music",
        fontSize = 24.sp,
        color = arrayMyColor[1],
        modifier = Modifier
            .padding(2.dp,12.dp)
    )
}

@Composable
fun StartButton(navController: NavHostController) {
    val myColors = MyColors()
    val arrayMyColor = myColors.colorList

    TextButton(
        onClick = { navController.navigate(Rutas.MainActivity.ruta) },
        modifier = Modifier.padding(2.dp),
        colors = ButtonDefaults.buttonColors(containerColor = arrayMyColor[4]),
        border = BorderStroke(4.dp, arrayMyColor[2])
    ) {
        Text(
            text = "LEt's g0!?",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            color = arrayMyColor[2],
            modifier = Modifier.padding(2.dp)
        )
    }
}