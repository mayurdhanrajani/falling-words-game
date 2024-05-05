package com.fallingwords.game.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fallingwords.home.model.datamodel.Word
import com.fallingwords.utils.FallingWordsAppConstants.CONVERT_MILLIS
import com.fallingwords.utils.FallingWordsAppConstants.DEFAULT_INT_VALUE
import com.fallingwords.utils.FallingWordsAppConstants.GAME_WORDS_COUNT
import com.fallingwords.utils.FallingWordsAppConstants.TIME_INTERVAL
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/** This ViewModel is used to observe the LiveData of game related variables based on which the UI is updated with being lifecycle aware **/

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {

    /** This variable sets the value based on the words passed from previous screen **/
    private val _words = MutableLiveData<List<Word>>()

    /** This variable sets the value based on the current word that is being presented **/
    private val _word = MutableLiveData<Word>()

    /** This variable observes whether the value is changed based on the current word that is being presented **/
    val word: LiveData<Word> = _word

    /** This variable sets the value of the current index of the word list passed from previous screen **/
    private val _currentWordIndex = MutableLiveData<Int>()

    /** This variable observes the value of the current index of the word list passed from previous screen **/
    val currentWordIndex: LiveData<Int> = _currentWordIndex

    /** This variable sets the value when all words are presented and the game is finished **/
    private val _isGameFinished = MutableLiveData<Boolean>()

    /** This variable observes whether the value is changed when all words are presented and the game is finished **/
    val isGameFinished: LiveData<Boolean> = _isGameFinished

    /** This variable will store the instance of CountDownTimer **/
    private lateinit var timer: CountDownTimer

    /** This function will set the words passed from previous screen **/
    fun setWords(words: List<Word>) {
        _words.value = words
    }

    /** This function will show the next word either after 5 seconds interval or when user gives the answer **/
    fun showWord() {
        var index = _currentWordIndex.value ?: DEFAULT_INT_VALUE
        val timeIntervalInMillis = TIME_INTERVAL.times(CONVERT_MILLIS)
        val remainingTime = GAME_WORDS_COUNT.minus(index).times(timeIntervalInMillis)

        timer = object :
            CountDownTimer(remainingTime, timeIntervalInMillis) {
            override fun onTick(millisUntilFinished: Long) {
                _word.value = _words.value?.get(index)
                _currentWordIndex.value = index++
            }

            override fun onFinish() {
                _isGameFinished.value = true
            }
        }
        timer.start()
    }

    /** This function will cancel the existing timer and will show the next word when user gives the answer **/
    fun nextWord() {
        timer.cancel()
        _currentWordIndex.value = _currentWordIndex.value?.plus(1) ?: DEFAULT_INT_VALUE
        showWord()
    }

}