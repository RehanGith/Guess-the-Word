package com.example.guesstheword.screens.game


import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val word = MutableLiveData<String>()
    private val score = MutableLiveData<Int>()
    lateinit private var wordList: MutableList<String>
    private val currentTime = MutableLiveData<Long>()
    private val isFinishedGame = MutableLiveData<Boolean>()

    companion object {
        private const val DONE = 0L
        private const val ONE_SECOND = 1000L
        private const val SIXTY_SECOND = 2000L
    }

    private val timer: CountDownTimer

    init {
        score.value = 0
        word.value = ""
        currentTime.value = 0
        isFinishedGame.value = false
        resetList()
        nextWord()
        timer = object : CountDownTimer(SIXTY_SECOND, ONE_SECOND) {
            override fun onTick(p0: Long) {
                currentTime.value = (p0 / ONE_SECOND)
            }

            override fun onFinish() {
                currentTime.value = DONE
                isFinishedGame.value = true
            }

        }
        timer.start()


    }

    fun getScore(): LiveData<Int> {
        return score
    }


    fun getWord(): LiveData<String> {
        return word
    }

    fun getFinishedState(): LiveData<Boolean> {
        return isFinishedGame
    }

    fun getCurrentTime(): LiveData<Long> {
        return currentTime
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
            resetList()
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
        timer.cancel()
    }
}