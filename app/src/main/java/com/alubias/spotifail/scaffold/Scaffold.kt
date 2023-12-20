package com.alubias.spotifail.scaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alubias.spotifail.R
import com.alubias.spotifail.ui.theme.MyColors
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun MyScaffold() {
        var presses by remember { mutableIntStateOf(0) }

        val myColors = MyColors()
        val arrayMyColor = myColors.colorList
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = arrayMyColor[0],
                    titleContentColor =  arrayMyColor[4],
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
                                .padding(20.dp,0.dp),
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
                            .size(36.dp),
                        tint = arrayMyColor[2]
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.home_24),
                        contentDescription = "home button",
                        modifier = Modifier
                            .size(36.dp),
                        tint = arrayMyColor[2]
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.headphones_24),
                        contentDescription = "jukebox button",
                        modifier = Modifier
                            .size(36.dp),
                        tint = arrayMyColor[2]
                    )

                }

            }
        },
        containerColor = arrayMyColor[1]
    ) { innerPadding -> //substitude for the Surface and the navigation

        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = ""

            )
        }
    }
    }

