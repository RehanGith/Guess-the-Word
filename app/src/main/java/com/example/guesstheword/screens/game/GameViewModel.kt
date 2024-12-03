package com.example.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.guesstheword.R

class GameViewModel: ViewModel() {
    val word = MutableLiveData<String>()
    val score = MutableLiveData<Int>()
    lateinit var wordList: MutableList<String>
    init {
        score.value = 0
        word.value = ""
        resetList()
        nextWord()

    }

    fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Called when the game is finished
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            //    gameFinished()
        } else {
            word.value = wordList.removeAt(0)
        }
    }
    fun onSkip() {
        score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        score.value = (score.value)?.plus(1)
        nextWord()
    }


    /** Methods for updating the UI **/
    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "onCleared called")
    }
}