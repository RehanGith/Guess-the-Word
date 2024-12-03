package com.example.guesstheword.screens.score

import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.app.ShareCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guesstheword.R

class ScoreViewModel(final_score: Int) : ViewModel() {
    private val score = MutableLiveData<Int>()

    init {
        score.value = final_score
    }

    fun getScore(): LiveData<Int> {
        return score
    }


}