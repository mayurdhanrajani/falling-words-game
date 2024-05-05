package com.fallingwords.di

import com.fallingwords.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/** This module provides the dependencies for Networking operations **/
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    /** This variable stores the Base URL for performing network operations **/
    private const val BASE_URL =
        "https://gist.githubusercontent.com/DroidCoder/7ac6cdb4bf5e032f4c737aaafe659b33/raw/baa9fe0d586082d85db71f346e2b039c580c5804/"

    /** This function provides the dependency of Moshi Converter **/
    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    /** This function provides the dependency of HttpLoggingInterceptor **/
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        return httpLoggingInterceptor
    }

    /** This function provides the dependency of OkHttpClient **/
    @Provides
    @Singleton
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    /** This function provides the dependency of Retrofit **/
    @Provides
    @Singleton
    fun provideRetrofit(factory: Moshi, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(factory))
        .client(client)
        .build()

}