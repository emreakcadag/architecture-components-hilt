package com.emreakcadag.architecturecomponents_hilt.feature.main

import com.emreakcadag.architecturecomponents_hilt.base.network.BaseRetrofitService
import com.emreakcadag.architecturecomponents_hilt.feature.main.data.MainApiService
import com.emreakcadag.architecturecomponents_hilt.feature.main.data.MainRepository
import com.emreakcadag.architecturecomponents_hilt.feature.main.data.source.MainLocalDataSource
import com.emreakcadag.architecturecomponents_hilt.feature.main.data.source.MainRemoteDataSource
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
object MainModule {

    @Provides
    @Singleton
    fun provideMainRemoteDataSource(baseRetrofitService: BaseRetrofitService) =
        MainRemoteDataSource(baseRetrofitService.create(MainApiService::class.java))

    @Provides
    @Singleton
    fun provideMainLocalDataSource() = MainLocalDataSource()

    @Provides
    @Singleton
    fun provideMainRepository(
        mainRemoteDataSource: MainRemoteDataSource,
        mainLocalDataSource: MainLocalDataSource
    ) = MainRepository(mainRemoteDataSource, mainLocalDataSource)
}