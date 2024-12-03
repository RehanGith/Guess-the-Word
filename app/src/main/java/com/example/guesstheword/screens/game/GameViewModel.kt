package com.example.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.guesstheword.R

class GameViewModel: ViewModel() {
    private val word = MutableLiveData<String>()
    private val score = MutableLiveData<Int>()
    lateinit private var wordList: MutableList<String>
    private val isFinishedGame = MutableLiveData<Boolean>()
    init {
        score.value = 0
        word.value = ""
        isFinishedGame.value = false
        resetList()
        nextWord()

    }
    fun getScore(): LiveData<Int> {
        return score
    }
    fun getWord() : LiveData<String> {
        return word
    }
    fun getFinishedState(): LiveData<Boolean> {
        return isFinishedGame
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
            isFinishedGame.value = true
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

    fun changeFinishedState() {
        isFinishedGame.value = false
    }
    /** Methods for updating the UI **/
    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "onCleared called")
    }
}