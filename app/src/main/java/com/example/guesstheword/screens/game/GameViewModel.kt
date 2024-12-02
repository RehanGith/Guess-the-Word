package com.example.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.guesstheword.R

class GameViewModel: ViewModel() {
    init {
        Log.i("GameViewModel", "initialize GameViewModel")
        resetList()
        nextWord()

    }
    var word = ""
    var score:Int = 0
    lateinit var wordList: MutableList<String>

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
            word = wordList.removeAt(0)
        }
    }
    fun onSkip() {
        score--
        nextWord()
    }

    fun onCorrect() {
        score++
        nextWord()
    }


    /** Methods for updating the UI **/
    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "onCleared called")
    }
}