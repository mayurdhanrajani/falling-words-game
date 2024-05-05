package com.fallingwords.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.io.IOException

/** This file stores the functions to perform data operations **/

/** This function fetches data from network, stores it in database and if any error occurs it fetches it from database.
 *  It returns the state using NetworkState as Loading, Error or Success state **/
suspend fun <T> fetchAndStoreData(
    fetchLocalDataQuery: suspend () -> T?,
    fetchRemoteDataRequest: suspend () -> Response<T?>?,
    insertLocalDataQuery: suspend (T?) -> Unit
): Flow<NetworkState<T>> =
    flow {
        emit(NetworkState.Loading(true))

        val localData = fetchLocalDataQuery.invoke()
        try {
            val remoteData = fetchRemoteDataRequest.invoke()
            if (remoteData != null && remoteData.isSuccessful) {
                insertLocalDataQuery(remoteData.body())
                emit(NetworkState.Success(remoteData.body()))
            } else {
                emit(NetworkState.Success(localData))
                emit(NetworkState.Error(remoteData?.message().toString()))
            }
        } catch (exception: IOException) {
            exception.printStackTrace()
            emit(NetworkState.Success(localData))
            emit(NetworkState.Error(exception.message.toString()))
        } finally {
            emit(NetworkState.Loading(false))
        }
    }.flowOn(Dispatchers.IO)