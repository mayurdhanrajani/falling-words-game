package com.fallingwords.di

import android.content.Context
import androidx.room.Room
import com.fallingwords.db.utils.DatabaseConstants.DATA_BASE_NAME
import com.fallingwords.db.app_db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** This module provides the dependencies of Database **/
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    /** This function provides the dependency of AppDatabase **/
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DATA_BASE_NAME
        ).build()

}