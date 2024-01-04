package com.alubias.spotifail.model

import androidx.lifecycle.ViewModel
import com.alubias.spotifail.R
import kotlinx.coroutines.flow.MutableStateFlow

class loginModel : ViewModel() {
    private val _songName = MutableStateFlow("")
    var songName = _songName

    private val _artistName = MutableStateFlow("")
    var artistName = _artistName

    private val _albumImage = MutableStateFlow(R.drawable.testphoto)
    var albumImage = _albumImage
}