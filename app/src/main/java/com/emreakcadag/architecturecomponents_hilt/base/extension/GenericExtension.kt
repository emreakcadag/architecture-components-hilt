package com.emreakcadag.architecturecomponents_hilt.base.extension

import android.util.Log
import com.emreakcadag.architecturecomponents_hilt.BuildConfig
import com.emreakcadag.architecturecomponents_hilt.base.network.BaseResponse

/**
 * Created by Emre Akçadağ on 15.08.2020
 */

/*
 * Get tag for all classes
 */
inline val <reified T> T.TAG: String get() = T::class.java.simpleName

/*
 * try - catch - finally block extension function.
 * @returns the given function in tryBlock or null.
 */
inline fun <T> tryCatch(
    tryBlock: () -> T?,
    catchBlock: (Throwable) -> Unit = {},
    finallyBlock: () -> Unit = {}
) = try {
    tryBlock()?.run {
        takeUnless { this is Unit }
    }
} catch (e: Exception) {
    catchBlock(e)
    null
} finally {
    finallyBlock()
}

/*
 * Log received message, works only debug mode
 */
inline fun <reified T> T.logDebug(message: Any?, logTag: String = "LogDebug: ${this.TAG}", isError: Boolean = false) {
    if (BuildConfig.DEBUG && message != null) {
        tryCatch({
            if (isError) Log.e(logTag, "$message") else Log.d(logTag, "$message")
        })
    }
}

/*
* Checks and return true if data contains nonnull DialogBox field
*/
fun <T> T.dialogBoxChecker(): Boolean {
    return if (this is BaseResponse) {
        this.dialogBox != null
    } else false
}