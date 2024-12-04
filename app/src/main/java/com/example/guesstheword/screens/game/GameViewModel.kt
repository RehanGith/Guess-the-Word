package com.example.guesstheword.screens.game


import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 2000)
val NO_BUZZ_PATTERN = longArrayOf(0)
class GameViewModel : ViewModel() {
    private val word = MutableLiveData<String>()
    private val score = MutableLiveData<Int>()
    lateinit private var wordList: MutableList<String>
    private val _buzzType = MutableLiveData<BuzzType>()
    val buzzType: LiveData<BuzzType>
        get() = _buzzType
    private val currentTime = MutableLiveData<Long>()

    private val isFinishedGame = MutableLiveData<Boolean>()
    enum class BuzzType(val pattern: LongArray){
        CORRECT_BUZZ(CORRECT_BUZZ_PATTERN),
        PANIC_BUZZ(PANIC_BUZZ_PATTERN),
        GAME_OVER_BUZZ(GAME_OVER_BUZZ_PATTERN),
        NO_BUZZ(NO_BUZZ_PATTERN)
    }
    companion object {
        private const val DONE = 0L
        private const val ONE_SECOND = 1000L
        private const val COUNT_DOWN_PANIC_SECONDS = 10L
        private const val SIXTY_SECOND = 60000L
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
                if(p0 / ONE_SECOND <= COUNT_DOWN_PANIC_SECONDS) {
                    _buzzType.value = BuzzType.PANIC_BUZZ
                } else {
                    _buzzType.value = BuzzType.NO_BUZZ
                }
            }

            override fun onFinish() {
                currentTime.value = DONE
                _buzzType.value = BuzzType.GAME_OVER_BUZZ
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
        _buzzType.value = BuzzType.CORRECT_BUZZ
        nextWord()
    }

    fun changeFinishedState() {
        isFinishedGame.value = false
    }
    fun onBuzzComplete() {
        _buzzType.value = BuzzType.NO_BUZZ
    }

    /** Methods for updating the UI **/
    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}