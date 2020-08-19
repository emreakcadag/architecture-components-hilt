package com.emreakcadag.architecturecomponents_hilt.feature.main.data

import com.emreakcadag.architecturecomponents_hilt.base.network.BaseRepository
import com.emreakcadag.architecturecomponents_hilt.feature.main.data.source.MainLocalDataSource
import com.emreakcadag.architecturecomponents_hilt.feature.main.data.source.MainRemoteDataSource
import javax.inject.Inject

/**
 * Created by Emre Akçadağ on 17.08.2020
 */
class MainRepository @Inject constructor(
    val mainRemoteDataSource: MainRemoteDataSource,
    val mainLocalDataSource: MainLocalDataSource
) : BaseRepository()