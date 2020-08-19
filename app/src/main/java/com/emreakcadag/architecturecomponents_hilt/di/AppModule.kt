package com.emreakcadag.architecturecomponents_hilt.di

import com.emreakcadag.architecturecomponents_hilt.base.applicationlistener.BaseActivityListener
import com.emreakcadag.architecturecomponents_hilt.base.applicationlistener.BaseActivityRetriever
import com.emreakcadag.architecturecomponents_hilt.base.navigator.Navigator
import com.emreakcadag.architecturecomponents_hilt.base.network.util.DialogBoxHandler
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
    fun provideNavigator() = Navigator()

    @Provides
    @Singleton
    fun provideBaseActivityListener() = BaseActivityListener()

    @Provides
    @Singleton
    fun provideBaseActivityRetriever(baseActivityListener: BaseActivityListener) =
        BaseActivityRetriever(baseActivityListener)

    @Provides
    @Singleton
    fun provideDialogBoxHandler(baseActivityRetriever: BaseActivityRetriever) =
        DialogBoxHandler(baseActivityRetriever)
}