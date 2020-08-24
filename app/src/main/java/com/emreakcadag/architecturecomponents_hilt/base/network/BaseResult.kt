package com.emreakcadag.architecturecomponents_hilt.base.network

/**
 * Created by Emre Akçadağ on 17.08.2020
 */
sealed class BaseResult<out T : Any?> {
    data class Success<out T : Any?>(val data: T?) : BaseResult<T?>()
    data class Error(val exception: Exception) : BaseResult<Nothing>()
    object Loading : BaseResult<Nothing>()
}