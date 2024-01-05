package com.alubias.spotifail.model

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.alubias.spotifail.R
import kotlinx.coroutines.flow.MutableStateFlow

class loginModel(private val context: Context) : ViewModel() {
    private val _songList = arrayOf(
        Song("Los Pokimones", "Josue Yiron", R.drawable.josueyiron, R.raw.josue),
    )
    var songList = _songList

    var mediaPlayer: MediaPlayer? = null

    private val _isPlaying = MutableStateFlow(false)
    var isPlaying = _isPlaying

    fun startSong(selectedSong: Song) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, selectedSong.soundtrack)
        }
        if (!mediaPlayer?.isPlaying!!) {
            mediaPlayer?.start()
            _isPlaying.value = true
        }
    }

    fun stopSong() {
        mediaPlayer?.pause()
        _isPlaying.value = false
    }
}