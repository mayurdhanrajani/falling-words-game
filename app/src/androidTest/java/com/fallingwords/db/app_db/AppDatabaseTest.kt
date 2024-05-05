package com.fallingwords.db.app_db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fallingwords.db.dao.WordsDao
import com.fallingwords.home.model.datamodel.Word
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/** This Test AppDatabase will run different types of test queries related to words **/
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    /** This variable will store the instance of WordsDao **/
    private lateinit var wordsDao: WordsDao

    /** This variable will store the instance of AppDatabase **/
    private lateinit var appDatabase: AppDatabase

    /** This function will be called before the beginning of the test to create the database **/
    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        wordsDao = appDatabase.wordsDao()
    }

    /** This test will be called to insert a word, fetch it from database and compare whether the word was saved **/
    @Test
    @Throws(Exception::class)
    fun writeWordAndReadInList() = runTest {
        val word = Word(id = 1, englishText = "beach", spanishText = "playa", isCorrectTranslation = true)

        val wordList = listOf(word)
        wordsDao.insertWords(wordList)

        val fetchedWords = wordsDao.fetchWords()

        Truth.assertThat(fetchedWords).contains(word)
    }

    /** This function will be called after the end of the test to close the database **/
    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        appDatabase.close()
    }

}