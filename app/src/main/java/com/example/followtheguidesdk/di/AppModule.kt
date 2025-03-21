package com.example.followtheguidesdk.di

import com.mapfactor.sdk.MpfcEngine
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMapfactorSDKEngine() : MpfcEngine = MpfcEngine.getInstance()
}