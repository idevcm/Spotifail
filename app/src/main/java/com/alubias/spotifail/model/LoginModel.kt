package com.alubias.spotifail.model

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.ViewModel
import com.alubias.spotifail.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginModel(private val context: Context) : ViewModel() {
    private val _songList = arrayOf(
        Song("Dale Zelda Dale", "Cucui Ganon Rosario", R.drawable.zelda, R.raw.zelda),
        Song("Los Pokimones", "Josue Yiron", R.drawable.josueyiron, R.raw.josue),
        Song("Manos en el Ano", "K0i", R.drawable.manosenelano, R.raw.manos),
        Song("Toothless Dancing Meme", "Dancing Dragon Merch", R.drawable.toothless, R.raw.toothless),
        Song("MoluscoTv", "Jovani Vázquez", R.drawable.jovanivazquez, R.raw.jovani),
        Song("F*ck The USA", "Exploited", R.drawable.exploited, R.raw.exploited)
    )

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    private val _isRepeatMode = MutableStateFlow(false)
    val isRepeatMode: StateFlow<Boolean> = _isRepeatMode

    private val _selectedSongIndex = MutableStateFlow(0)

    private val _selectedSong = MutableStateFlow(_songList[_selectedSongIndex.value])
    var selectedSong = _selectedSong

    var mediaPlayer: MediaPlayer? = null

    fun startSong(selectedSong: Song) {
        if (mediaPlayer?.currentPosition == 0 || mediaPlayer?.currentPosition == null) {
            mediaPlayer?.reset()
            mediaPlayer = MediaPlayer.create(context, selectedSong.soundtrack).apply {
                setOnCompletionListener {
                    Log.d("TAG", "repeat mode: ${_isRepeatMode.value}=")
                    if (_isRepeatMode.value) {
                        playNextSong()
                    } else if (_selectedSongIndex.value == _songList.size - 1) {
                        _selectedSongIndex.value = 0
                        _selectedSong.value = _songList[_selectedSongIndex.value]
                        stopSong()
                    } else {
                        playNextSong()
                    }
                }
            }
        }
        mediaPlayer?.start()
        _isPlaying.value = true
    }

    fun startSong(songEntered: Int) {
        mediaPlayer = MediaPlayer.create(context, songEntered).apply {
            setOnCompletionListener {
                this.start()
            }
        }
        mediaPlayer?.start()
    }

    fun stopSong() {
        mediaPlayer?.currentPosition ?: 0
        mediaPlayer?.pause()
        _isPlaying.value = false
    }

    fun resumeSong(sliderPosition: Int) {
        mediaPlayer?.seekTo(sliderPosition)
        mediaPlayer?.start()
        _isPlaying.value = true
    }

    fun playNextSong() {
        _selectedSongIndex.value = (_selectedSongIndex.value + 1) % _songList.size
        if (_selectedSongIndex.value == 0 && !_isRepeatMode.value) {
            stopSong()
        } else {
            _selectedSong.value = _songList[_selectedSongIndex.value]
            mediaPlayer?.reset()
            startSong(_selectedSong.value)
        }
    }

    fun playPreviousSong() {
        _selectedSongIndex.value = (_selectedSongIndex.value - 1 + _songList.size) % _songList.size
        if (_selectedSongIndex.value == _songList.size - 1 && !_isRepeatMode.value) {
            stopSong()
        } else {
            _selectedSong.value = _songList[_selectedSongIndex.value]
            mediaPlayer?.reset()
            startSong(_selectedSong.value)
        }
    }

    fun shuffleSong() {
        _selectedSongIndex.value = (_songList.indices).random()
        _selectedSong.value = _songList[_selectedSongIndex.value]
        mediaPlayer?.reset()
        startSong(_selectedSong.value)
    }

    fun toggleRepeatMode() {
        _isRepeatMode.value = !_isRepeatMode.value
    }

    fun resetMediaPlayer() {
        mediaPlayer?.reset()
    }
}