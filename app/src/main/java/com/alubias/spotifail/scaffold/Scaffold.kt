package com.alubias.spotifail.scaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alubias.spotifail.R
import com.alubias.spotifail.navigation.Rutas
import com.alubias.spotifail.ui.theme.MyColors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold(navController: NavController, content: @Composable () -> Unit) {
    val myColors = MyColors()
    val arrayMyColor = myColors.colorList

    Scaffold(

        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = arrayMyColor[0],
                    titleContentColor = arrayMyColor[4],
                ),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back_24),
                            contentDescription = "go back arrow icon",
                            modifier = Modifier
                                .size(28.dp),
                            tint = arrayMyColor[4]
                        )
                        Text(
                            text = "SpotiFail",
                            color = arrayMyColor[4],
                            modifier = Modifier
                                .padding(20.dp, 0.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = arrayMyColor[0],
                contentColor = arrayMyColor[2],
                modifier = Modifier
                    .height(50.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.list_24),
                        contentDescription = "songlist button",
                        modifier = Modifier
                            .size(28.dp),
                        tint = arrayMyColor[2]
                    )

                    TextButton(onClick = {

                        navController.navigate(Rutas.Cover.ruta) }) {

                        Icon(
                            painter = painterResource(id = R.drawable.home_24),
                            contentDescription = "home button",
                            modifier = Modifier
                                .size(28.dp),
                            tint = arrayMyColor[2]
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.headphones_24),
                        contentDescription = "jukebox button",
                        modifier = Modifier
                            .size(28.dp),
                        tint = arrayMyColor[2]
                    )

                }

            }
        },
        containerColor = arrayMyColor[1]
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(), // Add this line
            verticalArrangement = Arrangement.spacedBy(25.dp),
        ) {
            content()
        }
    }
}

