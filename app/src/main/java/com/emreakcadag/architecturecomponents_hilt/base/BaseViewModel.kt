package com.emreakcadag.architecturecomponents_hilt.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

/**
 * Created by Emre Akçadağ on 15.08.2020
 */
abstract class BaseViewModel : ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext = parentJob + Dispatchers.Default

    protected val scope = CoroutineScope(coroutineContext)

    fun cancelAllRequests() = coroutineContext.cancel()
}