package com.fallingwords.home.model.repository

import com.fallingwords.home.model.datamodel.Word
import com.fallingwords.utils.NetworkState
import kotlinx.coroutines.flow.Flow

/** This interface has all the functions used to fetch the words from the remote/local and store it in the database **/
interface IWordsRepository {

    /** This function will fetch the words from remote/local and store it in database **/
    suspend fun fetchWords(): Flow<NetworkState<List<Word>>>

}