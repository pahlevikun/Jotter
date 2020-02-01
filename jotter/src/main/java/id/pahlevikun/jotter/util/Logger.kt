package id.pahlevikun.jotter.util

import android.util.Log

internal object Logger {

    internal var logEnabled: Boolean = true
    internal var tag = "Jotter"

    internal fun debug(message: String) {
        if (logEnabled) {
            Log.d(tag, ">> $message")
        }
    }

    internal fun error(message: String, throwable: Throwable) {
        if (logEnabled) {
            Log.e(tag, ">> $message", throwable)
        }
    }

}