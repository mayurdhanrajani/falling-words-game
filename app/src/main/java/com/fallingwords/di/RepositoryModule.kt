package com.fallingwords.di

import com.fallingwords.home.model.repository.IWordsRepository
import com.fallingwords.home.model.repository.WordsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/** This module provides the dependencies of Repositories **/
@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    /** This function binds the dependency of IWordsRepository **/
    @Binds
    fun provideWordsRepository(wordsRepository: WordsRepository): IWordsRepository

}