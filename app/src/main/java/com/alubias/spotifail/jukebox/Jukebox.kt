package com.alubias.spotifail.jukebox

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alubias.spotifail.R
import com.alubias.spotifail.ui.theme.MyColors

class Jukebox {
    @Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFF2D3A3A)
    @Composable
    fun Vertical(){
        Column (Modifier.fillMaxSize()) {
            DescriptionText(
                Modifier
                    .weight(1f)
                    .fillMaxSize())
            AlbumCover(
                Modifier
                    .weight(1f)
                    .fillMaxSize())
            SliderDisplay(
                Modifier
                    .weight(2f)
                    .fillMaxSize())
            ButtonDisplay(
                Modifier
                    .weight(2f)
                    .fillMaxSize())
        }
    }
    @Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFF2D3A3A,
        device = "spec:parent=pixel_5,orientation=landscape"
    )
    @Composable
    fun Horizontal(){
        Row (Modifier.fillMaxSize()) {
            Column (Modifier.fillMaxSize()){
                DescriptionText(
                    Modifier
                        .weight(1f)
                        .fillMaxSize())
                AlbumCover(
                    Modifier
                        .weight(1f)
                        .fillMaxSize())
            }
            Column (Modifier.fillMaxSize()){
                SliderDisplay(
                    Modifier
                        .weight(2f)
                        .fillMaxSize())
                ButtonDisplay(
                    Modifier
                        .weight(2f)
                        .fillMaxSize())
            }
        }
    }

    @Composable
    fun DescriptionText(modifier: Modifier = Modifier) {
        var SongName by remember {mutableStateOf("Song")}
        var ArtistName by remember {mutableStateOf("Artist")}

        val myColors = MyColors()
        val arrayMyColor = myColors.colorList

        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "Now Playing",
                fontWeight = FontWeight.Bold,
                fontSize = 46.sp,
                color = arrayMyColor[2],
                modifier = Modifier.padding(2.dp))
            Text(text = "$SongName - $ArtistName",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = arrayMyColor[5],
                modifier = Modifier.padding(2.dp))
        }
    }

    @Composable
    fun AlbumCover(modifier: Modifier = Modifier){
        var AlbumImage by remember {mutableStateOf(R.drawable.testphoto)}

        Box (Modifier.fillMaxWidth()){
            Image(painter = painterResource(id = AlbumImage),
                contentDescription = "Album Cover Image",
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(360.dp)
                    .align(Alignment.TopCenter)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(24.dp)),
            )
        }

    }
    @Preview
    @Composable
    fun SliderDisplay(modifier: Modifier = Modifier) {

        var songValue by remember {mutableStateOf(0f)}
        var songDuration by remember {mutableStateOf(0f)}

        val myColors = MyColors()
        val arrayMyColor = myColors.colorList
        Column (modifier = Modifier
            .padding(16.dp,2.dp)){
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
            Row (modifier = Modifier
                .padding(16.dp, 2.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = (songValue / 60).toString(),
                    fontSize = 24.sp,
                    color = arrayMyColor[3])
                Text(text = (songDuration / 60).toString(),
                    fontSize = 24.sp,
                    color = arrayMyColor[3])
            }
        }
    }

    @Composable
    fun ButtonDisplay(modifier: Modifier = Modifier) {
        val myColors = MyColors()
        val arrayMyColor = myColors.colorList

        Row (modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .weight(1f)
            ){
                Icon(
                    painter = painterResource(id = R.drawable.headphones_24),
                    contentDescription = "Mix Playlist",
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
                onClick = { /* TODO */ },
                modifier = Modifier
                    .weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.headphones_24),
                    contentDescription = "Repeat Playlist",
                    tint = arrayMyColor[4],
                    modifier = Modifier
                        .size(82.dp)
                )
            }
        }
    }
}
