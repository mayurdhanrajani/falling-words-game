package com.fallingwords.home.model.repository

import com.fallingwords.db.dao.WordsDao
import com.fallingwords.home.model.api.WordsApi
import com.fallingwords.home.model.datamodel.Word
import com.fallingwords.utils.NetworkState
import com.fallingwords.utils.fetchAndStoreData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/** This repository has all the functions used to fetch the words from the remote/local and store it in the database **/
@Singleton
class WordsRepository @Inject constructor(
    private val wordsApi: WordsApi,
    private val wordsDao: WordsDao
) : IWordsRepository {

    /** This function will fetch the words from remote/local and store it in database **/
    override suspend fun fetchWords(): Flow<NetworkState<List<Word>>> =
        fetchAndStoreData(
            fetchLocalDataQuery = { wordsDao.fetchWords() },
            fetchRemoteDataRequest = { wordsApi.fetchWords() },
            insertLocalDataQuery = { wordsDao.insertWords(it) }
        )

}