package com.example.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    init {
        Log.i("GameViewModel", "initialize GameViewModel")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "onCleared called")
    }
}