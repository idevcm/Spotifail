package com.alubias.spotifail.jukebox

import android.app.Activity
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alubias.spotifail.R
import com.alubias.spotifail.model.Song
import com.alubias.spotifail.model.loginModel
import com.alubias.spotifail.ui.theme.MyColors
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ticker

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun Responsive(actividad: Activity, navController: NavHostController, loginModel: loginModel) {
    val tamanyo = calculateWindowSizeClass(activity = actividad)
    println(tamanyo.widthSizeClass)
    val selectedSong = loginModel.songList.first()
    if (tamanyo.heightSizeClass == WindowHeightSizeClass.Compact) {
        Horizontal(loginModel, selectedSong)
    } else {
        Vertical(loginModel, selectedSong)
    }
}

@Composable
fun Vertical(loginModel: loginModel, selectedSong: Song) {
    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        StaticText()
        DescriptionText(loginModel, selectedSong)
        AlbumCover(loginModel, selectedSong)
        SliderDisplay(loginModel)
        ButtonDisplay(loginModel, selectedSong)
    }
}

@Composable
fun Horizontal(loginModel: loginModel, selectedSong: Song) {
    Row(
        Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            AlbumCover(loginModel, selectedSong)
        }
        Column(
            Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            DescriptionText(loginModel, selectedSong)
            SliderDisplay(loginModel)
            ButtonDisplay(loginModel, selectedSong)
        }
    }
}

@Composable
fun StaticText(){
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
    }
}

@Composable
fun DescriptionText(loginModel: loginModel, selectedSong: Song) {

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
            text = "${selectedSong.name}",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = arrayMyColor[5],
            modifier = Modifier.padding(2.dp)
        )
        Text(
            text = "${selectedSong.artist}",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = arrayMyColor[5],
            modifier = Modifier.padding(2.dp).alpha(0.55F)
        )
    }
}

@Composable
fun AlbumCover(loginModel: loginModel, selectedSong: Song) {

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            //Provisional
            painter = painterResource(id = selectedSong.photo),
            contentDescription = "Album Cover Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(240.dp)
                .padding(24.dp)
                .clip(RoundedCornerShape(24.dp)),
        )
    }

}

@OptIn(ObsoleteCoroutinesApi::class)
@Composable
fun SliderDisplay(loginModel: loginModel) {
    val songDuration = loginModel.mediaPlayer?.duration?.toFloat() ?: 0f
    var songPosition by remember { mutableStateOf(0f) }
    val isPlaying = loginModel.isPlaying.collectAsState()

    val myColors = MyColors()
    val arrayMyColor = myColors.colorList

    LaunchedEffect(key1 = isPlaying.value) {
        if (loginModel.isPlaying.value) {
            val tickerChannel = ticker(delayMillis = 1000, initialDelayMillis = 0)
            for (event in tickerChannel) {
                songPosition = loginModel.mediaPlayer?.currentPosition?.toFloat() ?: 0f
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp, 2.dp)
    ) {
        Slider(
            value = songPosition,
            onValueChange = { newPosition ->
                songPosition = newPosition
                loginModel.mediaPlayer?.seekTo(newPosition.toInt())
            },
            valueRange = 0f..songDuration,
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
                text = formatTime(songPosition),
                fontSize = 24.sp,
                color = arrayMyColor[3]
            )
            Text(
                text = formatTime(songDuration),
                fontSize = 24.sp,
                color = arrayMyColor[3]
            )
        }
    }
}

fun formatTime(timeInMillis: Float): String {
    val totalSeconds = (timeInMillis / 1000).toInt()
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}

@Composable
fun ButtonDisplay(loginModel: loginModel, selectedSong: Song) {
    var repeatMode by remember { mutableStateOf(false) }
    var repeatIcon by remember { mutableStateOf(R.drawable.repeat_off_24) }
    var shuffleMode by remember { mutableStateOf(false) }
    var shuffleIcon by remember { mutableStateOf(R.drawable.shuffle_off_24) }
    var playSong by remember { mutableStateOf(false) }
    var playIcon by remember { mutableStateOf(R.drawable.pause_24) }

    val myColors = MyColors()
    val arrayMyColor = myColors.colorList

    LaunchedEffect(playSong) {
        if (playSong) {
            loginModel.startSong(selectedSong)
        } else {
            loginModel.stopSong()
        }
    }

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
            onClick = { playSong = !playSong
                if (playSong) playIcon = R.drawable.play_24
                else playIcon = R.drawable.pause_24},
            modifier = Modifier
                .weight(1f)
        ) {
            Icon(
                painter = painterResource(id = playIcon),
                contentDescription = "Play",
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

