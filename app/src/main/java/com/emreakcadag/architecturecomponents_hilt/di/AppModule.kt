package com.emreakcadag.architecturecomponents_hilt.di

import com.emreakcadag.architecturecomponents_hilt.base.applicationlistener.BaseActivityListener
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Created by Emre Akçadağ on 15.08.2020
 */
@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideBaseActivityListener() = BaseActivityListener()
}