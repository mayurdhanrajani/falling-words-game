package com.fallingwords.di

import com.fallingwords.db.app_db.AppDatabase
import com.fallingwords.db.dao.WordsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** This module provides the dependencies for Database operations **/
@InstallIn(SingletonComponent::class)
@Module
object DaoModule {

    /** This function provides the dependency of WordsDao **/
    @Provides
    @Singleton
    fun provideWordsDao(appDatabase: AppDatabase): WordsDao =
        appDatabase.wordsDao()

}