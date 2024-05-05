package com.fallingwords.home.model.api

import com.fallingwords.home.model.datamodel.Word
import retrofit2.Response
import retrofit2.http.GET

/** This interface is used to create Words API for performing networking operations using Retrofit **/
interface WordsApi {

    /** This API is used to fetch the words in English and its translation in Spanish **/
    @GET("words.json")
    suspend fun fetchWords(): Response<List<Word>?>?

}