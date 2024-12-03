package com.example.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(final_score: Int): ViewModel() {

    init {
        Log.i("ScoreViewModel", "score $final_score")
    }

}