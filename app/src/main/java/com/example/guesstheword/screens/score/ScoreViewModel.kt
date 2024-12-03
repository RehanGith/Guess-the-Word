package com.example.guesstheword.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(final_score: Int) : ViewModel() {
    private val score = MutableLiveData<Int>()

    init {
        score.value = final_score
    }

    fun getScore(): LiveData<Int> {
        return score
    }


}