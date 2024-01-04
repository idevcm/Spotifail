package com.alubias.spotifail.jukebox

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alubias.spotifail.R
import com.alubias.spotifail.model.loginModel
import com.alubias.spotifail.ui.theme.MyColors

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun Responsive(actividad: Activity, navController: NavHostController, loginModel: loginModel){
    val tamanyo = calculateWindowSizeClass(activity = actividad)
    println(tamanyo.widthSizeClass)
    if(tamanyo.heightSizeClass == WindowHeightSizeClass.Compact){
        Horizontal(loginModel)
    }else{
        Vertical(loginModel)
    }
}

@Composable
fun Vertical(loginModel: loginModel) {
    Column(
        Modifier.fillMaxSize()
            .background(MyColors().colorList[1]),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DescriptionText(loginModel)
        AlbumCover(loginModel)
        SliderDisplay()
        ButtonDisplay()
    }
}

@Composable
fun Horizontal(loginModel: loginModel) {
    Row(
        Modifier.fillMaxSize()
        .background(MyColors().colorList[1])
    ) {
        Column(
            Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            AlbumCover(loginModel)
        }
        Column(
            Modifier
                .weight(1f)
                .fillMaxSize()) {
            DescriptionText(loginModel)
            SliderDisplay()
            ButtonDisplay()
        }
    }
}

@Composable
fun DescriptionText(loginModel: loginModel) {
    val songName = loginModel.songName
    val artistName = loginModel.artistName

    val myColors = MyColors()
    val arrayMyColor = myColors.colorList

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Now Playing",
            fontWeight = FontWeight.Bold,
            fontSize = 46.sp,
            color = arrayMyColor[2],
            modifier = Modifier.padding(2.dp)
        )
        Text(
            text = "$songName - $artistName",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = arrayMyColor[5],
            modifier = Modifier.padding(2.dp)
        )
    }
}

@Composable
fun AlbumCover(loginModel: loginModel) {

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            //Provisio
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Album Cover Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(240.dp)
                .padding(24.dp)
                .clip(RoundedCornerShape(24.dp)),
        )
    }

}

@Composable
fun SliderDisplay() {

    var songValue by remember { mutableStateOf(0f) }
    var songDuration by remember { mutableStateOf(0f) }

    val myColors = MyColors()
    val arrayMyColor = myColors.colorList
    Column(
        modifier = Modifier
            .padding(16.dp, 2.dp)
    ) {
        Slider(
            value = songValue,
            onValueChange = { sliderValue_ ->
                songValue = sliderValue_
            },
            onValueChangeFinished = {
                Log.d("MainActivity", "sliderValue = $songValue")
            },
            valueRange = 0f..10f,
            colors = SliderDefaults.colors(
                thumbColor = arrayMyColor[3],
                activeTrackColor = arrayMyColor[4],
                inactiveTrackColor = arrayMyColor[5]
            )
        )
        Row(
            modifier = Modifier
                .padding(16.dp, 2.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                String.format("%.2f", songValue / 60).replace(',', '.'),
                fontSize = 24.sp,
                color = arrayMyColor[3]
            )
            Text(
                String.format("%.2f", songDuration / 60).replace(',', '.'),
                fontSize = 24.sp,
                color = arrayMyColor[3]
            )
        }
    }
}

@Composable
fun ButtonDisplay() {
    var repeatMode by remember { mutableStateOf(false) }
    var repeatIcon by remember { mutableStateOf(R.drawable.repeat_off_24) }
    var shuffleMode by remember { mutableStateOf(false) }
    var shuffleIcon by remember { mutableStateOf(R.drawable.shuffle_off_24) }

    val myColors = MyColors()
    val arrayMyColor = myColors.colorList

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(
            onClick = { /* TODO */
                repeatMode = !repeatMode
                if (repeatMode) repeatIcon = R.drawable.repeat_on_24
                else repeatIcon = R.drawable.repeat_off_24
            },
            modifier = Modifier
                .weight(1f)
        ) {
            Icon(
                painter = painterResource(id = repeatIcon),
                contentDescription = "Repeat Playlist",
                tint = arrayMyColor[4],
                modifier = Modifier
                    .size(82.dp)
            )
        }
        TextButton(
            onClick = { /* TODO */ },
            modifier = Modifier
                .weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_right_24),
                contentDescription = "Previous Song",
                tint = arrayMyColor[3],
                modifier = Modifier
                    .size(82.dp)
                    .rotate(180F)
            )
        }
        TextButton(
            onClick = { /* TODO */ },
            modifier = Modifier
                .weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_circle_24),
                contentDescription = "Play",
                tint = arrayMyColor[4],
                modifier = Modifier
                    .size(82.dp)
                    .rotate(-90F)
            )
        }
        TextButton(
            onClick = { /* TODO */ },
            modifier = Modifier
                .weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_right_24),
                contentDescription = "Next Song",
                tint = arrayMyColor[3],
                modifier = Modifier
                    .size(82.dp)
            )
        }
        TextButton(
            onClick = { /* TODO */
                shuffleMode = !shuffleMode
                if (shuffleMode) shuffleIcon = R.drawable.shuffle_on_24
                else shuffleIcon = R.drawable.shuffle_off_24
            },
            modifier = Modifier
                .weight(1f)
        ) {
            Icon(
                painter = painterResource(id = shuffleIcon),
                contentDescription = "Shuffle Playlist",
                tint = arrayMyColor[4],
                modifier = Modifier
                    .size(82.dp)
            )
        }
    }
}
