package com.fallingwords.home.model.repository

import com.fallingwords.home.model.datamodel.Word
import com.fallingwords.utils.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/** This fake repository has all the functions used to fetch the words from the remote/local and store it in the database **/
@Singleton
class FakeWordsRepository @Inject constructor() : IWordsRepository {

    /** This function will fetch the words from remote/local and store it in database **/
    override suspend fun fetchWords(): Flow<NetworkState<List<Word>>> =
        flow {
            emit(getWords())
        }

    /** This function will return the words dummy data **/
    private fun getWords(): NetworkState.Success<List<Word>> =
        NetworkState.Success(
            listOf(
                Word(englishText = "beach", spanishText = "playa", isCorrectTranslation = true),
                Word(englishText = "adulthood", spanishText = "país", isCorrectTranslation = false),
                Word(englishText = "uncle", spanishText = "tío", isCorrectTranslation = true),
                Word(englishText = "spoon", spanishText = "cuchara", isCorrectTranslation = true),
                Word(englishText = "satisfied", spanishText = "satisfecho", isCorrectTranslation = true),
                Word(englishText = "branch", spanishText = "serpiente", isCorrectTranslation = false),
                Word(englishText = "castle", spanishText = "castillo", isCorrectTranslation = true),
                Word(englishText = "fiction", spanishText = "bellas letras", isCorrectTranslation = true),
                Word(englishText = "aunt", spanishText = "retrato", isCorrectTranslation = false),
                Word(englishText = "rice", spanishText = "arroz", isCorrectTranslation = true)
            )
        )

}