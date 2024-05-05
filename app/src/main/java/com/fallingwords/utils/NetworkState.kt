package com.fallingwords.utils

/** This sealed class is used to maintain different states of a network call **/
sealed class NetworkState<T> {

    /** The NetworkState is of Loading state **/
    data class Loading<T>(val loading: Boolean) : NetworkState<T>()

    /** The NetworkState is of Error state **/
    data class Error<T>(val message: String) : NetworkState<T>()

    /** The NetworkState is of Success state **/
    data class Success<T>(val data: T?) : NetworkState<T>()

}