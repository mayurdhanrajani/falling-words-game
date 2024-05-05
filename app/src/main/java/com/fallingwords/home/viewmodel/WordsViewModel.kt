package com.fallingwords.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fallingwords.home.model.datamodel.Word
import com.fallingwords.home.model.repository.IWordsRepository
import com.fallingwords.utils.FallingWordsAppConstants.GAME_WORDS_COUNT
import com.fallingwords.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/** This ViewModel is used to observe the LiveData of words related variables based on which the UI is updated with being lifecycle aware **/
@HiltViewModel
class WordsViewModel @Inject constructor(private val wordsRepository: IWordsRepository) :
    ViewModel() {

    /** This variable sets the value based on the loading state **/
    private val _loading = MutableLiveData<Boolean>()

    /** This variable observes whether the value is changed based on the loading state **/
    val loading: LiveData<Boolean> = _loading

    /** This variable sets the value based on the error state **/
    private val _error = MutableLiveData<String>()

    /** This variable observes whether the value is changed based on the error state **/
    val error: LiveData<String> = _error

    /** This variable sets the value based on the words fetched from the API or Database **/
    private val _words = MutableLiveData<List<Word>>()

    /** This variable observes whether the value is changed based on the words fetched from the API or Database **/
    val words: LiveData<List<Word>> = _words

    /** This function fetches all the words from the API or the database **/
    fun fetchWords() {
        viewModelScope.launch {
            wordsRepository.fetchWords().collectLatest {
                when (it) {
                    is NetworkState.Loading -> _loading.value = it.loading
                    is NetworkState.Error -> _error.value = it.message
                    is NetworkState.Success -> {
                        if (it.data?.isEmpty() == true) {
                            _words.value = listOf()
                        } else {
                            val gameWords = it.data?.shuffled()?.take(GAME_WORDS_COUNT)
                            val incorrectWordsCount = (1..5).shuffled().first()

                            for (i in 0 until incorrectWordsCount) {
                                val lastShuffledWordFromData =
                                    it.data?.get((0..it.data.size).shuffled().last())
                                val currentGameWord = gameWords?.get(i)
                                currentGameWord?.isCorrectTranslation =
                                    lastShuffledWordFromData?.spanishText == currentGameWord?.spanishText
                                currentGameWord?.spanishText =
                                    lastShuffledWordFromData?.spanishText.toString()
                            }

                            _words.value = gameWords?.shuffled()
                        }
                    }
                }
            }
        }
    }

}