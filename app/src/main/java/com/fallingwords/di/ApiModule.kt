package com.fallingwords.di

import com.fallingwords.home.model.api.WordsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/** This module provides the dependencies for API operations **/
@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    /** This function provides the dependency of WordsApi **/
    @Provides
    @Singleton
    fun provideWordsApi(retrofit: Retrofit): WordsApi =
        retrofit.create(WordsApi::class.java)

}