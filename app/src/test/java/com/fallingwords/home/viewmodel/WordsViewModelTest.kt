package com.fallingwords.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fallingwords.home.model.repository.FakeWordsRepository
import com.fallingwords.utils.MainCoroutineRule
import com.fallingwords.utils.getOrAwaitValueTest
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/** This Test ViewModel will run different types of tests related to words **/
@OptIn(ExperimentalCoroutinesApi::class)
class WordsViewModelTest {

    /** This variable swaps the background executor with a different one which executes each task synchronously **/
    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    /** This variable installs a TestCoroutineDispatcher for Dispatchers.Main **/
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    /** This variable stores the instance of WordsViewModel **/
    private lateinit var wordsViewModel: WordsViewModel

    /** This function is called before the start of the test **/
    @Before
    fun setUpViewModel() {
        wordsViewModel = WordsViewModel(FakeWordsRepository())
    }

    /** This function fetches the words and returns success with list **/
    @Test
    fun `testFetchWords, return success with List`() {
        runTest {
            wordsViewModel.fetchWords()
        }
        val response = wordsViewModel.words.getOrAwaitValueTest()
        Truth.assertThat(response).isNotEmpty()
    }

}